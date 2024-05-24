package com.hjy.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.hjy.component.MemoryUserRecordSpace;
import com.hjy.component.XfXhStreamClient;
import com.hjy.config.XfXhConfig;
import com.hjy.dto.InteractMsg;
import com.hjy.dto.MsgDTO;
import com.hjy.dto.RecordsArray;
import com.hjy.listener.XfXhWebSocketListener;
import com.hjy.pojo.Label;
import com.hjy.pojo.Problem;
import com.hjy.pojo.Result;
import com.hjy.pojo.Search;
import com.hjy.service.ProblemService;
import jakarta.annotation.Resource;
import okhttp3.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;
    @Resource
    private XfXhConfig xfXhConfig;

    @Resource
    private XfXhStreamClient xfXhStreamClient;

    @Resource
    private MemoryUserRecordSpace memoryUserRecordSpace;
    @GetMapping("/findall")
    public Result findAll(){
        List<Problem> problems;
        problems = problemService.findAll();
//        for(Problem problem : problems){
//            System.out.println(problem);
//        }
        return Result.success(problems);
    }

    @PostMapping("/search")
    public Result search(@RequestBody Search search){
        List<Problem> problems;
        problems = problemService.search(search.getDif(), search.getLabels(),search.getContent());
        for(Problem problem : problems){
            System.out.println(problem);
        }
        return Result.success(problems);
    }

    @GetMapping("/findByid/{pId}")
    public Result findById(@PathVariable Integer pId){
        Problem problem = problemService.findById(pId);
        return Result.success(problem);
    }

    // 使用 id 作为唯一用户的标识（区分不同用户）
    @GetMapping("/sendQuestion")
    public String question(@RequestParam("id") Long id, @RequestParam("question") String question) throws InterruptedException {
        if (StrUtil.isBlank(question)) {
            return "无效问题，请重新输入";
        }

        // 尝试锁定用户
        if (!memoryUserRecordSpace.tryLock(id)) {
            return "正在处理上次问题，请稍后再试";
        }

        // 获取连接令牌
        if(!xfXhStreamClient.operateToken(XfXhStreamClient.GET_TOKEN_STATUS)){
            // 释放锁
            memoryUserRecordSpace.unLock(id);
            return "当前大模型连接数过多，请稍后再试";
        }

        MsgDTO msgDTO = MsgDTO.createUserMsg(question);
        XfXhWebSocketListener listener = new XfXhWebSocketListener();
        // 组装上下文内容发送
        List<MsgDTO> msgList = memoryUserRecordSpace.getAllInteractMsg(id);

        msgList.add(msgDTO);
        WebSocket webSocket = xfXhStreamClient.sendMsg(UUID.randomUUID().toString().substring(0, 10), msgList, listener);
        if (webSocket == null) {
            // 归还令牌
            xfXhStreamClient.operateToken(XfXhStreamClient.BACK_TOKEN_STATUS);
            // 释放锁
            memoryUserRecordSpace.unLock(id);
            return "系统内部错误，请联系管理员";
        }
        try {
            int count = 0;
            // 为了避免死循环，设置循环次数来定义超时时长
            int maxCount = xfXhConfig.getMaxResponseTime() * 5;
            while (count <= maxCount) {
                Thread.sleep(200);
                if (listener.isWsCloseFlag()) {
                    break;
                }
                count++;
            }
            if (count > maxCount) {
                return "大模型响应超时，请联系管理员";
            }
            // 将记录添加到 memoryUserRecordSpace
            String answer = listener.getAnswer().toString();
            memoryUserRecordSpace.storeInteractMsg(id, new InteractMsg(MsgDTO.createUserMsg(question), MsgDTO.createAssistantMsg(answer)));
            return answer;
        } finally {
            // 关闭连接
            webSocket.close(1000, "");
            // 释放锁
            memoryUserRecordSpace.unLock(id);
            // 归还令牌
            xfXhStreamClient.operateToken(XfXhStreamClient.BACK_TOKEN_STATUS);
        }
    }

    // 测试使用，查看内存空间中所有的用户记录信息
    @GetMapping("/spaceInfo")
    public List<JSONObject> spaceInfo(){
        ConcurrentHashMap<Long, RecordsArray> userRecordMap = memoryUserRecordSpace.getUserRecordMap();
        ArrayList<JSONObject> infoList = new ArrayList<>(userRecordMap.size());
        for (Map.Entry<Long, RecordsArray> entry : userRecordMap.entrySet()) {
            RecordsArray recordsArray = entry.getValue();
            JSONObject data = new JSONObject();
            data.put("id",entry.getKey());
            data.put("allInteractMsg",recordsArray.getAllInteractMsg());
            data.put("status",recordsArray.getStatus());
            data.put("lock",recordsArray.isLock());
            infoList.add(data);
        }

        return infoList;
    }

}

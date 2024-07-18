package com.hjy.controller;

import com.hjy.pojo.Classes;
import com.hjy.pojo.Result;
import com.hjy.pojo.User;
import com.hjy.service.ClassService;
import com.hjy.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ClassName: ClassController
 * Package: com.hjy.controller
 * Description:
 *
 * @Author hjy
 * @Create 2024/7/17 15:03
 */
@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private ClassService classService;
    @GetMapping("/findAll")
    public Result findAll(){
        List<Classes> classes = classService.findAll();
        return Result.success(classes);
    }
    @PostMapping("/join")
    public Result joinClass(@RequestParam("cid") Integer cid){
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        classService.joinClass(cid,id);
        return Result.success();
    }

    @GetMapping("findById")
    public Result findById(){
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        User user = classService.findById(id);
        System.out.println(user);
        return Result.success(user);
    }
}

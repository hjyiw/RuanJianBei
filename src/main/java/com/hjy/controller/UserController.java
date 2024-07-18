package com.hjy.controller;

import com.hjy.pojo.Result;
import com.hjy.pojo.User;
import com.hjy.pojo.conditions;
import com.hjy.pojo.errors;
import com.hjy.service.UserService;
import com.hjy.utils.JwtUtil;
import com.hjy.utils.Md5Util;
import com.hjy.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Validated
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 注册功能
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password,String role) {
        //查询用户
        User u = userService.findByUserName(username);
        if (u == null) {
            //注册
            userService.register(username, password,role);
            return Result.success();
        } else {
            //占用
            return Result.error("用户名已被占用");
        }
    }

    /**
     * 登录功能
     * @param username  用户名
     * @param password  密码
     * @return
     */
    @PostMapping("/login")
    public Result Login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        User u = userService.findByUserName(username);
        if (u == null) {
            return Result.error("用户不存在！");
        }
        if (u.getPassword().equals(password)) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", u.getId());
            claims.put("username", u.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        } else {
            return Result.error("密码错误！");
        }
    }

    /**
     * 根据id查询用户详细信息
     * @return
     */
    @GetMapping("/userInfo")
    public Result<User> userInfo() {
        Map<String,Object> map = ThreadLocalUtil.get();
        String name = (String) map.get("username");
        User u = userService.findByUserName(name);
        return Result.success(u);

    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PutMapping("/update")
    //将请求参数封装成一个对象
    //添加validated注解使在实体类中的参数校验生效
    public Result update(@RequestBody @Validated User user){
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        user.setId(id);
        userService.update(user);
        return Result.success();
    }

    /**
     * 更新头像
     * @param avatarUrl
     * @return
     */
    @PatchMapping("/updateAvatar")
    //加上URL注解进行参数校验
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    /**
     * 修改密码
     * @param params
     * @return
     */
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestParam("currentPassword") String old_pwd
    , @RequestParam("newPassword") String new_pwd,
                            @RequestParam("confirmPassword") String re_pwd){
        //校验参数
        if(!StringUtils.hasLength(old_pwd)
                || !StringUtils.hasLength(new_pwd)
                || !StringUtils.hasLength(re_pwd)){
            return Result.error("缺少参数");
        }
        Map<String,Object> map = ThreadLocalUtil.get();
        String name = (String) map.get("username");
        User loginUser = userService.findByUserName(name);
        if(!loginUser.getPassword().equals(old_pwd)){
            return Result.error("原密码错误");
        }
        if(!new_pwd.equals(re_pwd)){
            return Result.error("两次填写的新密码不一致");
        }

        //更新密码
        userService.updatePwd(new_pwd);
        return Result.success();

    }

    /**
     * 查询学生列表
     * @param classId
     * @return
     */
    @GetMapping("/stuList")
    public Result queryStudentByClassId(@RequestParam("classId") Long classId) {
        // 调用服务层的方法，根据班级ID获取学生列表
        List<User> students =  userService.findStudentsByClassId(classId);
        return Result.success(students);
    }

    /**
     * 搜索学生
     * @param username
     * @param id
     * @param cid
     * @return
     */
    @PostMapping("/searchStu")
    public Result searchStudent( @RequestParam(value = "username", required = false) String username,
                                 @RequestParam(value = "id", required = false) Integer id,
                                 @RequestParam(value = "cid", required = true) Long cid) {
        // 调用服务层的方法，根据传入的参数搜索学生
        List<User> students =  userService.searchStudents(username, id, cid);
        return Result.success(students);
    }

    @GetMapping("/condition")
    public Result queryCondition(@RequestParam("id") Long id) {
        System.out.println(id);
        conditions con = userService.findCondition(id);
        return Result.success(con);
    }

    @GetMapping("/errors")
    public Result queryErrors(@RequestParam("id") Long id) {
        errors err = userService.findErrors(id);
        return Result.success(err);
    }
}

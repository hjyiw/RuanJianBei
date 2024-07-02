package com.hjy.controller;

import com.hjy.pojo.Result;
import com.hjy.pojo.User;
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
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //查询用户
        User u = userService.findByUserName(username);
        if (u == null) {
            //注册
            userService.register(username, password);
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

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params){
        //校验参数
        String old_pwd = params.get("old_pwd");
        String new_pwd = params.get("new_pwd");
        String re_pwd = params.get("re_pwd");
        if(!StringUtils.hasLength(old_pwd)
                || !StringUtils.hasLength(new_pwd)
                || !StringUtils.hasLength(re_pwd)){
            return Result.error("缺少参数");
        }
        Map<String,Object> map = ThreadLocalUtil.get();
        String name = (String) map.get("username");
        User loginUser = userService.findByUserName(name);
        if(!loginUser.getPassword().equals(Md5Util.getMD5String(old_pwd))){
            return Result.error("原密码错误");
        }
        if(!new_pwd.equals(re_pwd)){
            return Result.error("两次填写的新密码不一致");
        }

        //更新密码
        userService.updatePwd(new_pwd);
        return Result.success();

    }

//    @GetMapping("/userinfo")
//    public Result user_info(Integer id){
//        User user = userService.findById(id);
//        System.out.println(user);
//        return Result.success(user);
//    }
}

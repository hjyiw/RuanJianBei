package com.hjy.controller;

import com.hjy.pojo.Result;
import com.hjy.pojo.User;
import com.hjy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/userinfo")
    public Result user_info(Integer id){
        User user = userService.findById(id);
        return Result.success(user);
    }
}

package com.hjy.service.Impl;

import com.hjy.mapper.UserMapper;
import com.hjy.pojo.User;
import com.hjy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserService
 * Package: com.hjy.service
 * Description:
 *
 * @Author hjy
 * @Create 2024/5/20 23:35
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findById(Integer id) {
        User user = userMapper.findById(id);
        return user;
    }
}

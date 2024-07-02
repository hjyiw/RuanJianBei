package com.hjy.service.Impl;

import com.hjy.mapper.UserMapper;
import com.hjy.pojo.User;
import com.hjy.service.UserService;
import com.hjy.utils.Md5Util;
import com.hjy.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

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
    @Override
    public User findByUserName(String username) {
        User u = userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
        //加密
//        String md5String = Md5Util.getMD5String(password);
        //添加
        userMapper.register(username,password);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
    }
}

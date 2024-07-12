package com.hjy.service.Impl;

import com.hjy.mapper.UserMapper;
import com.hjy.pojo.User;
import com.hjy.pojo.conditions;
import com.hjy.pojo.errors;
import com.hjy.service.UserService;
import com.hjy.utils.Md5Util;
import com.hjy.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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

    @Override
    public List<User> findStudentsByClassId(Long classId) {
        List<User> users = userMapper.findStudentsByClassId(classId);
        for (User user : users) {
            user.getNickname();
        }
        return users;
    }

    @Override
    public List<User> searchStudents(String username, Integer id, Long cid) {
        List<User> users = userMapper.searchStudents(username,id, cid);
        for (User user : users) {
            System.out.println(user.getNickname());
        }
        return users;
    }

    @Override
    public conditions findCondition(Long id) {
        return userMapper.findCondition(id);
    }

    @Override
    public errors findErrors(Long id) {
        return userMapper.findErrors(id);
    }
}

package com.hjy.service;

import com.hjy.pojo.User;

/**
 * ClassName: UserService
 * Package: com.hjy.service.Impl
 * Description:
 *
 * @Author hjy
 * @Create 2024/5/20 23:33
 * @Version 1.0
 */
public interface UserService {
    User findById(Integer id);
    //根据用户名查询用户
    User findByUserName(String username);

    //注册
    void register(String username, String password);

    //更新用户信息
    void update(User user);

    //更新头像
    void updateAvatar(String avatarUrl);

    //修改密码
    void updatePwd(String newPwd);
}

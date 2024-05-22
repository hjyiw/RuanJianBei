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
}

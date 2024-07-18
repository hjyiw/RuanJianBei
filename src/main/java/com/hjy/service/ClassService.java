package com.hjy.service;

import com.hjy.pojo.Classes;
import com.hjy.pojo.User;

import java.util.List;

/**
 * ClassName: ClassService
 * Package: com.hjy.service
 * Description:
 *
 * @Author hjy
 * @Create 2024/7/17 15:05
 */
public interface ClassService {
    List<Classes> findAll();

    void joinClass(int cid, int id);

    User findById(Integer id);
}

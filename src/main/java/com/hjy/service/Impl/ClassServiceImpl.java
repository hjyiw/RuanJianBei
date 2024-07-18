package com.hjy.service.Impl;

import com.hjy.mapper.ClassMapper;
import com.hjy.pojo.Classes;
import com.hjy.pojo.Result;
import com.hjy.pojo.User;
import com.hjy.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * ClassName: ClassServiceImpl
 * Package: com.hjy.service.Impl
 * Description:
 *
 * @Author hjy
 * @Create 2024/7/17 15:06
 */
@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassMapper classMapper;
    @Override
    public List<Classes> findAll() {
        List<Classes> classes = classMapper.findAll();
        return classes;
    }

    @Override
    public void joinClass(int cid, int id) {
        classMapper.joinClass(cid, id);
    }

    @Override
    public User findById(Integer id) {
        User user = classMapper.findById(id);
        return user;
    }

}

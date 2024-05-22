package com.hjy.mapper;

import com.hjy.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {
    User findById(Integer id);
}

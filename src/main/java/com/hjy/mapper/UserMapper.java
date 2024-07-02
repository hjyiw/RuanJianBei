package com.hjy.mapper;

import com.hjy.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface UserMapper {
    User findById(Integer id);
    //根据用户名查询用户
    @Select("select * from user where username = #{username}")
    User findByUserName(String username);

    //注册
    @Insert("insert into user(username,password,create_time,update_time)" +
            " values(#{username},#{password},now(),now())")
    void register(String username, String password);

    //更新用户信息
    @Update("update user set nickname = #{nickname}, phone = #{phone}, update_time = #{updateTime}, signature = #{signature} where id = #{id}")
    void update(User user);

    //更新头像
    @Update("update user set picture = #{picture}, update_time = now() where id = #{id}")
    void updateAvatar(String avatarUrl, Integer id);

    //更新密码
    @Update("update user set password = #{newPwd}, update_time = now() where id = #{id}")
    void updatePwd(String newPwd, Integer id);
}

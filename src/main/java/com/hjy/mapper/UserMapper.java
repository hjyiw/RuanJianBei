package com.hjy.mapper;

import com.hjy.pojo.User;
import com.hjy.pojo.conditions;
import com.hjy.pojo.errors;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;


@Mapper
public interface UserMapper {
    User findById(Integer id);
    //根据用户名查询用户
    @Select("select username, id, password, " +
            "phone, nickname, signature, " +
            "picture, create_time, update_time," +
            " email, role, ac_num, c_id " +
            "from user" +
            "  where username = #{username}")
    User findByUserName(String username);

    //注册
    @Insert("insert into user(username,password,role,create_time,update_time)" +
            " values (#{username},#{password},#{role} ,now(),now())")
    void register(String username, String password,String role);

    //更新用户信息
    void update(User user);

    //更新头像
    @Update("update user set picture = #{picture}, update_time = now() where id = #{id}")
    void updateAvatar(String avatarUrl, Integer id);

    //更新密码
    @Update("update user set password = #{newPwd}, update_time = now() where id = #{id}")
    void updatePwd(String newPwd, Integer id);

    @Select("select * from user where c_id = #{classId} and role = 'student'")
    List<User> findStudentsByClassId(Long classId);

    List<User> searchStudents(String username, Integer id, Long cid);

    @Select("select * from `condition` where id = #{id}")
    conditions findCondition(Long id);

    @Select("select * from `errors` where s_id = #{id}")
    errors findErrors(Long id);
}

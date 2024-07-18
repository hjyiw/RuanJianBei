package com.hjy.mapper;

import com.hjy.pojo.Classes;
import com.hjy.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * ClassName: ClassMapper
 * Package: com.hjy.mapper
 * Description:
 *
 * @Author hjy
 * @Create 2024/7/17 15:05
 */
@Mapper
public interface ClassMapper {
    @Select("select * from class")
    public List<Classes> findAll();

    @Update("update user set c_id = #{cid} where id = #{id} ")
    void joinClass(int cid, int id);

    @Select("select username, id, password, " +
            "phone, nickname, signature, " +
            "picture, create_time, update_time," +
            " email, role, ac_num, c.c_id,c.c_name  " +
            "from user join class c on c.c_id = user.c_id " +
            "  where id = #{id}")
    User findById(Integer id);
}

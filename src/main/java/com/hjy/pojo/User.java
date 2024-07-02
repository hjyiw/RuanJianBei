package com.hjy.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ClassName: User
 * Package: com.hjy.pojo
 * Description:
 *
 * @Author hjy
 * @Create 2024/5/21 14:25
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id; //id
    private String nickname; //昵称
    private String username; // 用户名
    private String phone; //电话
    @JsonIgnore //返回前端忽略密码
    private String password; // 密码
    private String signature; //签名
    private String picture; // 头像
    private String email; //邮箱
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //更新时间
}

package com.hjy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: Search
 * Package: com.hjy.pojo
 * Description:
 *
 * @Author hjy
 * @Create 2024/5/22 12:55
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Search {
    private String dif;
    private List<String> labels; // 假设Label是一个已有的实体类
    private String content;
}

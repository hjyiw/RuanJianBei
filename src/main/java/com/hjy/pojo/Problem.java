package com.hjy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: Problem
 * Package: com.hjy.pojo
 * Description:
 *
 * @Author hjy
 * @Create 2024/5/21 19:06
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Problem {
    private Integer pId; // 题目id
    private String pName; // 题目名字
    private Integer state; //状态
    private String difficulty; // 难度
    private Integer acNum; //通过数
    private String content; //内容
    private List<Label> labels; //标签
    private String inDetail; //输入描述
    private String outDetail; //输出描述
    private String inTest; //测试输入
    private String OutTest; //测试输出
}

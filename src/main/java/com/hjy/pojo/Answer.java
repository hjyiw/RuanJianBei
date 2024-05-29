package com.hjy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: Answer
 * Package: com.hjy.pojo
 * Description:
 *
 * @Author hjy
 * @Create 2024/5/28 22:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    Integer result; //运行结果
    List<String> sug; //建议
}

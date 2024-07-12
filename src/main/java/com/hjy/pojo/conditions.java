package com.hjy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: condition
 * Package: com.hjy.pojo
 * Description:
 *
 * @Author hjy
 * @Create 2024/7/8 12:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class conditions {
    private Integer UndefinedDeclaration; //声明未定义
    private Integer MemoryLeak; //内存泄漏
    private Integer ArrayOut; //数组越界
    private Integer TypeError; //类型转换错误
    private Integer MisingSemi; //缺少分号
    private Integer id; //学号



}

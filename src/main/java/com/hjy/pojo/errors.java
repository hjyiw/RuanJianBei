package com.hjy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: errors
 * Package: com.hjy.pojo
 * Description:
 *
 * @Author hjy
 * @Create 2024/7/8 12:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class errors {
    private Integer sId;
    private String timeOut;
    private String correct;
    private String outOfMem;
    private String compileErr;
    private String other;
}

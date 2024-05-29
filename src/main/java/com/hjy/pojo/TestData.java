package com.hjy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * ClassName: TestData
 * Package: com.hjy.pojo
 * Description:
 *
 * @Author hjy
 * @Create 2024/5/27 23:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestData {
    private String input;
    private String output;

    @Override
    public String toString() {
        return "input: "+ input + "\toutput: "  + output;
    }
}

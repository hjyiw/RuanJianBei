package com.hjy.codetest;

import com.alibaba.fastjson.JSON;
import com.hjy.pojo.Answer;
import com.hjy.pojo.TestData;
import com.hjy.service.ProblemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CodeTestApplicationTests {

    @Autowired
    private ProblemService problemService;
    @Test
    void searchTest(){
        List<String> labels = new ArrayList<>();
        labels.add("1");
        labels.add("5");
        String l = "1";
        System.out.println(labels.contains(l));
    }

    @Test
    void dataTest(){
        List<TestData> datas = problemService.findData(1);
        String content = problemService.findContent(1);
        String question = "\n这是一段代码，题目是：'"+ content + "'数据集如下：\n";
        int cnt = 1;
        for(TestData data : datas){
            question += "sample" + cnt + ": " + data.toString() + "\n";
            cnt ++;
        }
        System.out.println(question);
    }
    @Test
    void codeTest(){
        String jsonString = "{\n"
                + "    \"result\": 0,\n"
                + "    \"sug\": [\"代码中的减法操作符应该是 '-'，而不是 '+'。\"]\n"
                + "}";

        // 使用fastjson解析JSON字符串到Answer对象
        Answer answer = JSON.parseObject(jsonString, Answer.class);
        System.out.println(answer);
        // 打印结果
//        System.out.println("Result: " + answer.getResult());
//        System.out.println("Suggestions:");
//        for (String suggestion : answer.getSug()) {
//            System.out.println(suggestion);
//        }
    }

}

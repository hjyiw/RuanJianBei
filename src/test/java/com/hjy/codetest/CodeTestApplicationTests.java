package com.hjy.codetest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

//@SpringBootTest
class CodeTestApplicationTests {

    @Test
    void searchTest(){
        List<String> labels = new ArrayList<>();
        labels.add("1");
        labels.add("5");
        String l = "1";
        System.out.println(labels.contains(l));
    }

}

package com.hjy.service;

import com.hjy.pojo.Problem;
import com.hjy.pojo.TestData;

import java.util.List;

/**
 * ClassName: ProblemService
 * Package: com.hjy.service
 * Description:
 *
 * @Author hjy
 * @Create 2024/5/21 19:27
 * @Version 1.0
 */
public interface ProblemService {
    List<Problem> findAll();

    List<Problem> search(String dif, List<String> labels, String content);

    Problem findById(Integer pId);

    List<TestData> findData(Integer pId);

    String question(String question, Integer pId);

    String findContent(Integer pId);

    void updateState(Integer pId, Integer state);
}

package com.hjy.service;

import com.hjy.pojo.Label;
import com.hjy.pojo.Problem;

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

    List<Problem> search(String dif, List<Label> labels, String content);
}

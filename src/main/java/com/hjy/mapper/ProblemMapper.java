package com.hjy.mapper;

import com.hjy.pojo.Label;
import com.hjy.pojo.Problem;
import com.hjy.pojo.TestData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProblemMapper {
    List<Problem> findAll();

    List<Label> findLabel(Integer pId);

    List<Problem> search(String dif, String content);

    Problem findById(Integer pId);

    List<TestData> findData(Integer pId);

    String findContent(Integer pId);
}

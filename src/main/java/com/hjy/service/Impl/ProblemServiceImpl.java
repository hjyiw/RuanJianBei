package com.hjy.service.Impl;

import com.hjy.mapper.ProblemMapper;
import com.hjy.pojo.Label;
import com.hjy.pojo.Problem;
import com.hjy.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.List;

/**
 * ClassName: ProblemServiceImpl
 * Package: com.hjy.service.Impl
 * Description:
 *
 * @Author hjy
 * @Create 2024/5/21 19:27
 * @Version 1.0
 */
@Service
public class ProblemServiceImpl implements ProblemService {
    @Autowired
    private ProblemMapper problemMapper;

    @Override
    public List<Problem> findAll() {
        List<Problem> problems = problemMapper.findAll();
        for (Problem problem : problems) {
            List<Label> Labels = problemMapper.findLabel(problem.getPId());
            problem.setLabels(Labels);
        }
        return problems;
    }

    @Override
    public List<Problem> search(String dif, List<String> labels, String content) {
        List<Problem> problems = problemMapper.search(dif, content);
        //找到该题目对应所有算法标签
        for (Problem problem : problems) {
            List<Label> Labels = problemMapper.findLabel(problem.getPId());
            problem.setLabels(Labels);
        }
        if (labels != null && !labels.isEmpty()) {
            Iterator<Problem> iterator = problems.iterator();
            while(iterator.hasNext()) {
                Problem problem = iterator.next();
                boolean flag = false;
                for (Label label : problem.getLabels()) {
                    if (labels.contains(label.getLName())) {
                        flag = true;
                        break;
                    }
                }
                if(!flag)
                    iterator.remove();
            }
        }
        return problems;

    }

    @Override
    public Problem findById(Integer pId) {
        return problemMapper.findById(pId);
    }
}

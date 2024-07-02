package com.hjy.service.Impl;

import com.hjy.mapper.ProblemMapper;
import com.hjy.pojo.Label;
import com.hjy.pojo.Problem;
import com.hjy.pojo.TestData;
import com.hjy.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public String findContent(Integer pId){
        return problemMapper.findContent(pId);
    }
    @Override
    public List<TestData> findData(Integer pId) {
        return problemMapper.findData(pId);
    }

    @Override
    public String question(String question, Integer pId) {
        String content = findContent(pId);
        question += "\n这是一段代码，题目是：'"+ content + "'数据集如下：\n";
        int cnt = 1;
        List<TestData> datas = findData(pId);
        for(TestData data : datas){
            question += "sample" + cnt + ": " + data.toString() + "\n";
            cnt ++;
        }
        question += "解析这段代码，你给出的回复必须是一个json形式的对象，属性有result和sug,如果代码通过所有数据集result就为1否则为0,sug是一个数组存你对代码进行语法错误和规范检查后的建议,如果测试集没通过也要在sug里给出错误在哪";
        return question;
    }
}

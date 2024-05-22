package com.hjy.controller;

import com.hjy.pojo.Label;
import com.hjy.pojo.Problem;
import com.hjy.pojo.Result;
import com.hjy.pojo.Search;
import com.hjy.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;
    @GetMapping("/findall")
    public Result findAll(){
        List<Problem> problems;
        problems = problemService.findAll();
        return Result.success(problems);
    }

    @PostMapping("/search")
    public Result search(@RequestBody Search search){
        List<Problem> problems;
        problems = problemService.search(search.getDif(), search.getLabels(),search.getContent());
        return Result.success(problems);
    }
}

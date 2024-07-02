package com.hjy.exception;

import com.hjy.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ClassName: GlobalExceptionHandler
 * Package: com.hjy.exception
 * Description:
 *
 * @Author 黄嘉宇
 * @Create 2024/5/5 14:08
 * @Version 1.0
 */
@RestControllerAdvice //标注这个是用来处理异常的
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) //指定处理的异常类型
    public Result handleException(Exception e){
        e.printStackTrace();
        return Result.error(
                StringUtils.hasLength(e.getMessage())
                        ? e.getMessage()
                        : "操作失败"
                );
    }
}

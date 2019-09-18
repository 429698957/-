package com.tensquare.base.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

/**
 * 公共异常处理类
 *
 * 控制层抛出异常后都由BaseExceptionHandler拦截
 * 统一进行处理，返回result对象
 */
@RestControllerAdvice //控制层通知
public class BaseExceptionHandler {
    /**
     * 异常处理方法
     */
    @ExceptionHandler(Exception.class)

    public Result error(Exception e){
        String message = e.getMessage();
        if(e instanceof EmptyResultDataAccessException){
            message="数据为空";
        }
        System.out.println("报异常了！！！！！！");

        return new Result(false, StatusCode.ERROR, message);

    }

}

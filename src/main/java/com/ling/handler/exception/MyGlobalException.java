package com.ling.handler.exception;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/5
 * @ Time 16:48
 */

import com.ling.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class MyGlobalException {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exception(Exception e){
        log.error(e.toString());
        e.printStackTrace();
        return Result.error();
    }
    @ExceptionHandler(MyRuntimeException.class)
    @ResponseBody
    public Result myRuntimeException(MyRuntimeException e){
        log.error(e.getMessage());
        return Result.error().code(e.getCode()).message(e.getMessage());
    }
}

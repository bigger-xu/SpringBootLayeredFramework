package com.cto.freemarker.filter;


import com.cto.freemarker.exception.CustomException;
import com.cto.freemarker.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Bigger-Xu
 * @version v1.0.1
 * @date 2020/8/29 17:11
 */
//@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = CustomException.class)
    public Result handleCustomException(CustomException e){
        return Result.error(e.getCode(),e.getCustomMessage());
    }
    @ExceptionHandler(value = Exception.class)
    public Result handleException(Exception e){
        return Result.error(e.getMessage());
    }
}

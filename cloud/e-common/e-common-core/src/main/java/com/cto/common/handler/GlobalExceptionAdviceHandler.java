package com.cto.common.handler;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import cn.dev33.satoken.exception.NotLoginException;
import com.cto.common.constants.CommonResultCode;
import com.cto.common.constants.HrsResultCode;
import com.cto.common.exception.CtoBizException;
import com.cto.common.exception.CtoServerException;
import com.cto.common.response.MessageInfo;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常拦截处理器
 *
 * @author 张永伟
 * @since 2023/4/24
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdviceHandler {

    /**
     * 全局异常Exception拦截
     *
     * @param e 异常信息
     * @return Object-->MessageInfo
     * @since 2023/4/24
     */
    @ExceptionHandler(value = Exception.class)
    public Object handleGlobalException(Exception e) {
        log.error("全局异常拦截 Exception ===> " + e.getMessage(), e);
        return MessageInfo.failed(CommonResultCode.SYSTEM_ERROR);
    }

    /**
     * 全局异常自定义异常CtoBizException拦截
     *
     * @param e 异常信息
     * @return Object-->MessageInfo
     * @since 2023/4/24
     */
    @ExceptionHandler(value = CtoBizException.class)
    public Object handleCustomException(CtoBizException e) {
        log.error("全局自定义异常拦截 CtoBizException ===> " + e.getMessage(), e);
        return MessageInfo.failed(e.getCode(), e.getMessage());
    }


    /**
     * 全局异常自定义异常CtoBizException拦截
     *
     * @param e 异常信息
     * @return Object-->MessageInfo
     * @since 2023/4/24
     */
    @ExceptionHandler(value = CtoServerException.class)
    public Object handleCustomServerException(CtoServerException e) {
        log.error("全局微服务状态自定义异常拦截 CtoServerException ===> " + e.getMessage(), e);
        return MessageInfo.failed(e.getCode(), e.getMessage());
    }


    /**
     * 全局SA授权异常NotLoginException拦截
     *
     * @param e 异常信息
     * @return Object-->MessageInfo
     * @since 2023/4/24
     */
    @ExceptionHandler(value = NotLoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Object handleNotLoginException(NotLoginException e) {
        //        log.error("全局授权异常拦截 CtoBizException ===> " + e.getMessage(), e);
        return MessageInfo.failed(HrsResultCode.INVALID_TOKEN_ERROR);
    }


    /**
     * validation Exception 验证类异常，后续看下有没有用到，没有用到可以删除
     *
     * @param e MethodArgumentNotValidException BindException
     * @return R
     */
    @ExceptionHandler({ MethodArgumentNotValidException.class, BindException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageInfo<String> handleBodyValidException(Exception e) {
        BindingResult bindingResult;
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ec = (MethodArgumentNotValidException) e;
            bindingResult = ec.getBindingResult();
        } else {
            BindException ec = (BindException) e;
            bindingResult = ec.getBindingResult();
        }
        List<String> errMsg = bindingResult.getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());
        String msg = errMsg.toString();
        log.error("参数requestBody验证异常 ===> {}", msg);
        return MessageInfo.failed(msg);
    }

    /**
     * 捕获param参数异常 验证类异常，后续看下有没有用到，没有用到可以删除
     *
     * @param e ConstraintViolationException
     * @return MessageInfo
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageInfo<String> handleParamValidException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> cvs = e.getConstraintViolations();
        List<String> errList = cvs.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        String msg = errList.toString();
        log.error("参数requestParam验证异常 ===> {}", msg);
        return MessageInfo.failed(msg);
    }

}

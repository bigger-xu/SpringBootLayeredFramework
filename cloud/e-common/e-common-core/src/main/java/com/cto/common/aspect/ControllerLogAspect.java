package com.cto.common.aspect;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author libiao
 */
@Slf4j
@Aspect
@Component
public class ControllerLogAspect {

    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<>();

    private static HttpServletRequest getHttpServletRequest() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        if (ra instanceof ServletRequestAttributes) {
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            return sra.getRequest();
        }
        return null;
    }

    @Pointcut("execution(public * com.cto..controller..*Controller.*(..))")
    public void controllerEntries() {
        // do nothing
    }

    @Before("controllerEntries()")
    public void before(JoinPoint jp) {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
        print(jp);
    }

    private void print(JoinPoint jp) {
        if (log.isDebugEnabled()) {
            HttpServletRequest request = getHttpServletRequest();
            if (Objects.nonNull(request)) {
                log.debug("URL : " + request.getRequestURL().toString());
                log.debug("HTTP_METHOD : " + request.getMethod());
                log.debug("IP : " + request.getRemoteAddr());
            }
        }
        if (log.isInfoEnabled()) {
            MethodSignature sg = (MethodSignature) jp.getSignature();
            String[] parameterNames = sg.getParameterNames();
            Object[] parameterValues = jp.getArgs();
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < parameterValues.length; i++) {
                String name = parameterNames[i];
                Object value = parameterValues[i];
                if (value instanceof ServletRequest || value instanceof ServletResponse) {
                    continue;
                }
                if (value instanceof MultipartFile) {
                    MultipartFile file = (MultipartFile) value;
                    map.put(name, file.getOriginalFilename());
                } else {
                    map.put(name, value);
                }
            }
            log.info("请求方法：{}.{} 请求参数: {}", sg.getDeclaringTypeName(), sg.getName(), JSONObject.toJSONString(map));
        }
    }

    @AfterReturning(pointcut = "controllerEntries()", returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {
        try {
            if (log.isInfoEnabled()) {
                Signature sg = jp.getSignature();
                long beginTime = TIME_THREADLOCAL.get();
                long endTime = System.currentTimeMillis();
                log.info("请求方法：{}.{} 请求耗时: {}ms", sg.getDeclaringTypeName(), sg.getName(), endTime - beginTime);
            }
        } finally {
            TIME_THREADLOCAL.remove();
        }
    }

}

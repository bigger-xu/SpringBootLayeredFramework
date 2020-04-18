package com.cto.freemarker.filter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 
 * @author Zhang Wei
 * @date 2020/4/18 14:33
 * @version v1.0.1
 */
@Aspect
@Component
public class MapperAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapperAspect.class);

    /**
     * 声明环绕通知
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.nanoTime();
        Object obj = pjp.proceed();
        long end = System.nanoTime();
        LOGGER.info("调用Mapper方法：{}，参数：{}，执行耗时：{}纳秒，耗时：{}毫秒",pjp.getSignature().toString(), Arrays.toString(pjp.getArgs()),(end - begin), (end - begin) / 1000000);
        return obj;
    }

    /**
     * 监控com.cto.freemarker.dao..*Mapper包及其子包的所有public方法
     */
    @Pointcut("execution(* com.cto.freemarker.dao..*.*(..))")
    private void pointCutMethod() {
    }
}

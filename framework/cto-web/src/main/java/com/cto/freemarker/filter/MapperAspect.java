package com.cto.freemarker.filter;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 
 * @author Zhang Wei
 * @date 2020/4/18 14:33
 * @version v1.0.1
 */
//@Aspect
//@Component
@Slf4j
public class MapperAspect {

    /**
     * 监控com.cto.freemarker.dao..*Mapper包及其子包的所有public方法
     */
    @Pointcut("execution(* com.cto.freemarker.mapper.*.*(..))")
    private void pointCutMethod() {
    }

    /**
     * 声明该方法执行之前执行，前置通知
     * 直接导入切面点，上面的
     * @param joinPoint
     */
    @Before("pointCutMethod()")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("切面开始打印,方法: {},参数: {}",methodName,Arrays.asList(args));
    }

    /**
     * 后置通知：在目标方法执行后（无论是否发生异常），执行通知
     * 在后置通知中还不能访问目标方法的执行的结果，不是在执行方法后调用的
     * 和--->8
     */
    @After("pointCutMethod()")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("切面结束打印,方法: {}",methodName);
    }


    /**
     * 带有返回值的切面
     * 在方法法正常结束受执行的代码
     * 返回通知是可以访问到方法的返回值的!
     * 可以使用returning = "result"进行获取后得到
     */
    @AfterReturning(value = "pointCutMethod()",returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        log.info("切面结束返回打印,方法: {},返回: {}",methodName,result);
    }

    /**
     * 异常处理切面
     * 在目标方法出现异常时会执行的代码.
     * 可以访问到异常对象; 且可以指定在出现特定异常时在执行通知代码
     */
    @AfterThrowing(value = "pointCutMethod()",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " occurs excetion:" + e);
    }

    /**
     * 声明环绕通知
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.nanoTime();
        Object obj = pjp.proceed();
        long end = System.nanoTime();
        log.info("调用Mapper方法：{}，执行耗时：{}纳秒，耗时：{}毫秒",pjp.getSignature().toString(),(end - begin), (end - begin) / 1000000);
        return obj;
    }


}

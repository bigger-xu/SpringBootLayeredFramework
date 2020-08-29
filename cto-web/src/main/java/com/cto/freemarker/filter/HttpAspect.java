package com.cto.freemarker.filter;

import com.cto.freemarker.entity.AdminUser;
import com.cto.freemarker.entity.CustomLogs;
import com.cto.freemarker.entity.OperationLogs;
import com.cto.freemarker.service.OperationLogsService;
import com.cto.freemarker.utils.MapUtils;
import com.cto.freemarker.utils.ThreadPoolUtil;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhang Yongwei
 * @version 1.0
 * @date 2019-11-20
 */
@Aspect
@Component
public class HttpAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpAspect.class);
    private static final ThreadLocal<Date> beginTimeThreadLocal = new NamedThreadLocal<Date>("ThreadLocal beginTime");
    @Autowired
    private OperationLogsService operationLogsService;
    @Autowired(required = false)
    private HttpServletRequest request;


    /**
     * 定义切面，只置入带 @SystemLog 注解的方法或类
     * Controller层切点，注解方式
     *
     * @Pointcut("execution(* *..controller..*Controller*.*(..))")
     */
    @Pointcut("@annotation(com.cto.freemarker.entity.CustomLogs)")
    public void controllerAspect() {

    }

    /**
     * 前置通知 (在方法执行之前返回)用于拦截Controller层记录用户的操作的开始时间
     *
     * @param joinPoint 切点
     * @throws InterruptedException
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        //线程绑定变量（该数据只有当前请求的线程可见）
        Date beginTime = new Date();
        beginTimeThreadLocal.set(beginTime);
    }


    /**
     * 后置通知(在方法执行之后并返回数据) 用于拦截Controller层无异常的操作
     *
     * @param joinPoint 切点
     */
    @AfterReturning("controllerAspect()")
    public void after(JoinPoint joinPoint) {
        try {
            Map<String, Object> map = getControllerMethodInfo(joinPoint);
            Map<String, String[]> logParams = request.getParameterMap();
            AdminUser user = (AdminUser) SecurityUtils.getSubject().getPrincipal();
            OperationLogs log = new OperationLogs();
            //请求用户
            log.setCreateUserId(user.getId());
            //日志标题
            log.setDescription(map.get("description").toString());
            //日志类型
            log.setType(map.get("type").toString());
            //日志请求url
            log.setRequestUrl(request.getRequestURI());
            //请求参数
            log.setRequestParams(MapUtils.getMapToString(logParams));
            //请求参数
            log.setRequestParams(logParams.toString());
            //请求iP
            log.setIpAddress(request.getRemoteAddr());
            //请求开始时间
            long beginTime = beginTimeThreadLocal.get().getTime();
            long endTime = System.currentTimeMillis();
            //请求耗时
            Long logElapsedTime = endTime - beginTime;
            log.setRunTime(logElapsedTime);
            log.setCreateTime(new Date());
            //调用线程保存至log表
            ThreadPoolUtil.getPool().execute(() -> {
                operationLogsService.insert(log);
            });
        } catch (Exception e) {
            LOGGER.error("AOP后置通知异常", e);
        } finally {
            ThreadPoolUtil.getPool().shutdown();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static Map<String, Object> getControllerMethodInfo(JoinPoint joinPoint) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>(16);
        // 获取目标类名
        String targetName = joinPoint.getTarget().getClass().getName();
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取相关参数
        Object[] arguments = joinPoint.getArgs();
        // 生成类对象
        Class targetClass = Class.forName(targetName);
        // 获取该类中的方法
        Method[] methods = targetClass.getMethods();

        String description = "";
        String type = "";

        for (Method method : methods) {
            if (!method.getName().equals(methodName)) {
                continue;
            }
            Class[] clazzs = method.getParameterTypes();
            if (clazzs.length != arguments.length) {
                // 比较方法中参数个数与从切点中获取的参数个数是否相同，原因是方法可以重载
                continue;
            }
            description = method.getAnnotation(CustomLogs.class).description();
            type = method.getAnnotation(CustomLogs.class).type().getValue();
            map.put("description", description);
            map.put("type", type);
        }
        return map;
    }
}

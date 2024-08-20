package com.efreight.hrs.config;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author ZhangYongWei
 * @since 2023/12/20
 */
@Intercepts(value = {
        @Signature(type = StatementHandler.class, method = "query", args = { Statement.class, ResultHandler.class }),
        @Signature(type = StatementHandler.class, method = "update", args = { Statement.class }),
        @Signature(type = StatementHandler.class, method = "batch", args = { Statement.class })
})
@Slf4j
@Component
@RefreshScope
public class PrintSqlRunTimeInterceptor implements Interceptor {
    
    @Value("${slow-sql.max-cost-time:100}")
    private int maxCostTime;
    
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long beginTime = System.currentTimeMillis();
        try {
            return invocation.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            long costTime = endTime - beginTime;
            if (costTime > maxCostTime) {
                log.warn("<== 慢SQL语句产生: {}, 执行耗时: {}ms", printSql(invocation), costTime);
            }
        }
    }
    
    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Interceptor.super.plugin(target);
        }
        
        return target;
    }
    
    
    private String printSql(Invocation invocation) {
        try {
            return formatSql(invocation);
        } catch (Exception e) {
            log.error("自定义输出SQL执行日志异常", e);
        }
        return "";
    }
    
    /**
     * 格式化SQL及其参数
     *
     * @param invocation invocation
     * @return String
     * @since 2023/12/20
     */
    private String formatSql(Invocation invocation) throws NoSuchFieldException, IllegalAccessException {
        //获取StatementHandler
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        //获取ParameterHandler
        ParameterHandler parameterHandler = statementHandler.getParameterHandler();
        //获取boundSql
        BoundSql boundSql = statementHandler.getBoundSql();
        
        Class<? extends ParameterHandler> parameterHandlerClass = parameterHandler.getClass();
        Field mappedStatementField = parameterHandlerClass.getDeclaredField("mappedStatement");
        mappedStatementField.setAccessible(true);
        MappedStatement mappedStatement = (MappedStatement) mappedStatementField.get(parameterHandler);
        String sql = boundSql.getSql();
        // 输入sql字符串空判断
        if (Objects.isNull(sql)) {
            return "";
        }
        // 不传参数的场景，直接把Sql美化一下返回出去
        Object parameterObject = parameterHandler.getParameterObject();
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappings();
        if (Objects.isNull(parameterObject) || parameterMappingList.isEmpty()) {
            // 美化sql
            sql = beautifySql(sql).toLowerCase();
            return sql;
        }
        // 定义一个没有替换过占位符的sql，用于出异常时返回
        String sqlWithoutReplacePlaceholder = sql;
        try {
            sql = handleCommonParameter(boundSql, mappedStatement);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            // 占位符替换过程中出现异常，则返回没有替换过占位符但是格式美化过的sql
            return sqlWithoutReplacePlaceholder;
        }
        // 美化sql
        sql = beautifySql(sql).toLowerCase();
        return sql;
    }
    
    /**
     * 美化SQL
     *
     * @param sql sql
     * @return String
     * @since 2023/12/20
     */
    private String beautifySql(String sql) {
        sql = sql.replaceAll("[\\s\n ]+", " ");
        return sql;
    }
    
    /**
     * 替换SQL中的?,设置sql参数
     *
     * @param boundSql        sql
     * @param mappedStatement 参数
     * @return String
     * @since 2023/12/20
     */
    private String handleCommonParameter(BoundSql boundSql, MappedStatement mappedStatement) {
        String sql = boundSql.getSql();
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        Configuration configuration = mappedStatement.getConfiguration();
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        for (ParameterMapping parameterMapping : parameterMappings) {
            if (parameterMapping.getMode() != ParameterMode.OUT) {
                Object value;
                String propertyName = parameterMapping.getProperty();
                if (boundSql.hasAdditionalParameter(propertyName)) {
                    value = boundSql.getAdditionalParameter(propertyName);
                } else if (parameterObject == null) {
                    value = null;
                } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                    value = parameterObject;
                } else {
                    MetaObject metaObject = configuration.newMetaObject(parameterObject);
                    value = metaObject.getValue(propertyName);
                }
                sql = replacePlaceholder(sql, value);
            }
        }
        return sql;
    }
    
    /**
     * 根据不同的propertyValue类型,匹配SQL?的类型并替换值
     *
     * @param sql           sql
     * @param propertyValue 类型
     * @return String
     * @since 2023/12/20
     */
    private String replacePlaceholder(String sql, Object propertyValue) {
        String value;
        if (Objects.nonNull(propertyValue)) {
            if (propertyValue instanceof String) {
                value = "'" + propertyValue + "'";
            } else if (propertyValue instanceof Date) {
                value = "'" + DATE_TIME_FORMATTER
                        .format(((Date) propertyValue).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                        + "'";
            } else if (propertyValue instanceof LocalDate) {
                value = "'" + DATE_FORMATTER.format((LocalDate) propertyValue) + "'";
            } else if (propertyValue instanceof LocalDateTime) {
                value = "'" + DATE_TIME_FORMATTER.format((LocalDateTime) propertyValue) + "'";
            } else {
                value = propertyValue.toString();
            }
        } else {
            value = "null";
        }
        return sql.replaceFirst("\\?", value);
    }
    
}

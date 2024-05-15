package com.cto.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis-Plus 配置信息
 *
 * @author 张永伟
 * @since 2023/7/3
 */
@Configuration
@MapperScan("com.cto.**.dao")
public class MybatisPlusConfig {


    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        //分页插件
        PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor();
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        paginationInterceptor.setMaxLimit(-1L);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setOptimizeJoin(true);
        paginationInterceptor.setDbType(DbType.MYSQL);
        //乐观锁插件
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        mybatisPlusInterceptor.addInnerInterceptor(paginationInterceptor);
        return mybatisPlusInterceptor;
    }

    /**
     * 开启xml Map返回转驼峰
     *
     * @return ConfigurationCustomizer
     * @since 2023/7/3
     */
    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return configuration -> configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());
    }

    /**
     * 采用mybatis-plus自带的分布式ID生成策略
     *
     * @return MybatisPlusPropertiesCustomizer
     * @since 2023/7/3
     */
    @Bean
    public MybatisPlusPropertiesCustomizer plusPropertiesCustomizer() {
        return plusProperties -> plusProperties.getGlobalConfig().setIdentifierGenerator(new DefaultIdentifierGenerator());
    }
}

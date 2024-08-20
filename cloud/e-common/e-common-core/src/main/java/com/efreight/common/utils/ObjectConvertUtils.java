package com.efreight.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.efreight.common.constants.CommonResultCode;
import com.efreight.common.exception.EfreightBizException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;

/**
 * 对象拷贝工具类
 *
 * @author 张永伟
 * @since 2023/8/18
 */
@Slf4j
public class ObjectConvertUtils {
    
    /**
     * 对象拷贝
     *
     * @param source 来源
     * @param target 目标
     */
    public static <T, R> void beanCopyProperties(T source, R target) {
        BeanUtils.copyProperties(source, target);
    }
    
    public static <T, R> R beanCopyProperties(T source, Class<R> rClass) {
        if (source != null) {
            try {
                R target = rClass.getDeclaredConstructor().newInstance();
                BeanUtils.copyProperties(source, target);
                return target;
            } catch (Exception e) {
                log.error("根据class创建对象异常：{}", e.getMessage());
            }
        }
        return null;
    }
    
    
    /**
     * Page分页拷贝
     *
     * @param source      来源
     * @param target      目标
     * @param targetClass 要转换的实体对象的Class
     */
    public static <R, T> void pageCopyProperties(Page<R> source, Page<T> target, Class<T> targetClass) {
        List<T> collect = source.getRecords().stream().map(o -> {
            T targetBean;
            try {
                targetBean = targetClass.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                throw new EfreightBizException(CommonResultCode.SYSTEM_ERROR, e);
            }
            BeanUtils.copyProperties(o, targetBean);
            return targetBean;
        }).collect(Collectors.toList());
        BeanUtils.copyProperties(source, target);
        target.setRecords(collect);
    }
    
    /**
     * List集合拷贝
     *
     * @param source      List源数据
     * @param targetClass 要转换的实体对象的class
     * @return 实体对象对应的List
     */
    public static <R, T> List<T> listCopyProperties(List<R> source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        return source.stream().map(o -> {
            T targetBean;
            try {
                targetBean = targetClass.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                throw new EfreightBizException(CommonResultCode.SYSTEM_ERROR, e);
            }
            BeanUtils.copyProperties(o, targetBean);
            return targetBean;
        }).collect(Collectors.toList());
    }
}

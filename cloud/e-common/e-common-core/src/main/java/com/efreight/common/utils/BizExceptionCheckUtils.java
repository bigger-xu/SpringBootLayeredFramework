package com.efreight.common.utils;

import java.util.Collection;
import java.util.Objects;

import com.efreight.common.constants.ResultCode;
import com.efreight.common.exception.EfreightBizException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.util.CollectionUtils;

/**
 * 业务代码校验，异常处理工具类
 *
 * @author 张永伟
 * @since 2023/4/28
 */
public class BizExceptionCheckUtils {


    /**
     * 校验为true，则丢出自定义异常返回给前端，用于判断参数校验等
     *
     * @param expression 是否为true
     * @param resultCode 需要丢出的异常，不同的服务，传不同的Code对象，如ScResultCode
     * @since 2023/4/28
     */
    public static void isTrue(boolean expression, ResultCode resultCode) {
        if (expression) {
            throw new EfreightBizException(resultCode);
        }
    }

    /**
     * 校验为true,异常信息可自定义
     * public static final ScResultCode SC_NOT_READ = new ScResultCode(180039, "订单参数读取失败，订单号为%s!");
     * isTrue("强制关闭".equals(afOrder.getOrderStatus()), ScResultCode.SC_NOT_READ, afOrder.getId());
     *
     * @param expression 是否为true
     * @param resultCode 需要丢出的异常，不同的服务，传不同的Code对象，如ScResultCode
     * @param params     传入的参数
     * @since 2023/4/28
     */
    public static void isTrue(boolean expression, ResultCode resultCode, Object... params) {
        if (expression) {
            throw new EfreightBizException(resultCode.getCode(), String.format(resultCode.getMsg(), params));
        }
    }

    /**
     * 校验对象为空，则丢出自定义异常返回给前端，用于判断参数校验等
     *
     * @param object     判断对象
     * @param resultCode 需要丢出的异常，不同的服务，传不同的Code对象，如ScResultCode
     * @since 2023/4/28
     */
    public static void isNull(Object object, ResultCode resultCode) {
        isTrue(Objects.isNull(object), resultCode);
    }
    /**
     * 校验对象为空，则返回false
     *
     * @param object     判断对象
     * @since 2023/9/7
     */
    public static Boolean isNull(Object object) {
        return Objects.isNull(object);
    }

    /**
     * 校验对象为空，异常信息可自定义
     *
     * @param object     判断对象
     * @param resultCode 需要丢出的异常，不同的服务，传不同的Code对象，如ScResultCode
     * @param params     传入的参数
     * @since 2023/4/28
     */
    public static void isNull(Object object, ResultCode resultCode, Object... params) {
        isTrue(Objects.isNull(object), resultCode, params);
    }

    /**
     * 校验value为空
     *
     * @param value      字符串
     * @param resultCode 需要丢出的异常，不同的服务，传不同的Code对象，如ScResultCode
     */
    public static void isNull(String value, ResultCode resultCode) {
        isTrue(StringUtils.isEmpty(value), resultCode);
    }

    /**
     * 校验value为空，则返回false
     *
     * @param value     字符串
     * @since 2023/9/7
     */
    public static Boolean isNull(String value) {
        return StringUtils.isEmpty(value);
    }
    /**
     * 校验value为空 异常信息自定义
     *
     * @param value      字符串
     * @param resultCode 需要丢出的异常，不同的服务，传不同的Code对象，如ScResultCode
     * @param params     传入的参数
     */
    public static void isNull(String value, ResultCode resultCode, Object... params) {
        isTrue(StringUtils.isEmpty(value), resultCode, params);
    }

    /**
     * 校验 collection 为 empty
     *
     * @param collection 集合
     * @param resultCode 需要丢出的异常，不同的服务，传不同的Code对象，如ScResultCode
     */
    public static void isNull(Collection<?> collection, ResultCode resultCode) {
        isTrue(CollectionUtils.isEmpty(collection), resultCode);
    }

    /**
     * 校验collection为空，则返回false
     *
     * @param collection     集合
     * @since 2023/9/7
     */
    public static Boolean isNull(Collection<?> collection) {
        return CollectionUtils.isEmpty(collection);
    }
    /**
     * 校验 collection 为 empty  异常信息自定义
     *
     * @param collection 集合
     * @param resultCode 需要丢出的异常，不同的服务，传不同的Code对象，如ScResultCode
     * @param params     传入的参数
     */
    public static void isNull(Collection<?> collection, ResultCode resultCode, Object... params) {
        isTrue(CollectionUtils.isEmpty(collection), resultCode, params);
    }

    /**
     * 数组 为 empty
     * <p>为 empty 则抛异常</p>
     *
     * @param array      数组
     * @param resultCode 需要丢出的异常，不同的服务，传不同的Code对象，如ScResultCode
     */
    public static void isNull(Object[] array, ResultCode resultCode) {
        isTrue(ArrayUtils.isEmpty(array), resultCode);
    }

    /**
     * 数组 为 empty
     *
     * @param array      数组
     * @since 2023/9/7
     */
    public static Boolean isNull(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }

    /**
     * 数组 为 empty
     * <p>为 empty 则抛异常</p>
     *
     * @param array      数组
     * @param resultCode 需要丢出的异常，不同的服务，传不同的Code对象，如ScResultCode
     * @param params     传入的参数
     */
    public static void isNull(Object[] array, ResultCode resultCode, Object... params) {
        isTrue(ArrayUtils.isEmpty(array), resultCode, params);
    }
}

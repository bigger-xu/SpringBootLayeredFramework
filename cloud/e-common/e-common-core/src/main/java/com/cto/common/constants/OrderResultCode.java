package com.cto.common.constants;

/**
 * afbase返回状态码
 *
 * @author 张永伟
 * @since 2023/4/24
 */
public class OrderResultCode extends ResultCode {

    public static final OrderResultCode ORDER_ERROR = new OrderResultCode(100001, "订单错误!");

    public static final OrderResultCode ORDER_CARGOTRACK = new OrderResultCode(100002, "套餐量已用完!");

    public static final OrderResultCode USER_NOTEXSIT_ERROR = new OrderResultCode(100003, "用户不存在!");

    public static final OrderResultCode ORDER_ATTACHMENT_REACHED_MAXIMUM = new OrderResultCode(100009, "订单附件已达上限,无法新增!");

    public static final OrderResultCode ORDER_DETAIL_IS_NOT_NULL = new OrderResultCode(100010, "订单信息不能为空!");

    public static final OrderResultCode FREEMARKER_TEMPLATE_ERROR = new OrderResultCode(100011, "Freemarker报文组装失败!");

    public static final OrderResultCode PRINT_MESSAGE_ERROR = new OrderResultCode(100012, "报文组装失败,查询信息为空!");

    public static final OrderResultCode PRINT_MESSAGE_SYSTEM_VALIDATE_ERROR = new OrderResultCode(100013, "报文校验失败,查询信息为空!");

    public static final OrderResultCode PRINT_MESSAGE_PARAMS_VALIDATE_ERROR = new OrderResultCode(100014, "报文校验失败:%s");

    public static final OrderResultCode PRINT_MESSAGE_NO_PERMISSION = new OrderResultCode(100015,
            "对不起，贵司没有开通%s的权限，请联系管理员开通相关权限!");


    public OrderResultCode(Integer code, String msg) {
        super(code, msg);
    }
}

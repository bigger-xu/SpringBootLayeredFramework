package com.efreight.common.constants;

/**
 * afbase返回状态码
 *
 * @author 张永伟
 * @since 2023/4/24
 */
public class AfbaseResultCode extends ResultCode {

    public static final AfbaseResultCode ORDER_ERROR = new AfbaseResultCode(100001, "订单错误!");

    public static final AfbaseResultCode ORDER_CARGOTRACK = new AfbaseResultCode(100002, "套餐量已用完!");

    public static final AfbaseResultCode USER_NOTEXSIT_ERROR = new AfbaseResultCode(100003, "用户不存在!");

    public static final AfbaseResultCode ORDER_ATTACHMENT_REACHED_MAXIMUM = new AfbaseResultCode(100009, "订单附件已达上限,无法新增!");

    public static final AfbaseResultCode ORDER_DETAIL_IS_NOT_NULL = new AfbaseResultCode(100010, "订单信息不能为空!");

    public static final AfbaseResultCode FREEMARKER_TEMPLATE_ERROR = new AfbaseResultCode(100011, "Freemarker报文组装失败!");

    public static final AfbaseResultCode PRINT_MESSAGE_ERROR = new AfbaseResultCode(100012, "报文组装失败,查询信息为空!");

    public static final AfbaseResultCode PRINT_MESSAGE_SYSTEM_VALIDATE_ERROR = new AfbaseResultCode(100013, "报文校验失败,查询信息为空!");

    public static final AfbaseResultCode PRINT_MESSAGE_PARAMS_VALIDATE_ERROR = new AfbaseResultCode(100014, "报文校验失败:%s");

    public static final AfbaseResultCode PRINT_MESSAGE_NO_PERMISSION = new AfbaseResultCode(100015,
            "对不起，贵司没有开通%s的权限，请联系管理员开通相关权限!");


    public AfbaseResultCode(Integer code, String msg) {
        super(code, msg);
    }
}

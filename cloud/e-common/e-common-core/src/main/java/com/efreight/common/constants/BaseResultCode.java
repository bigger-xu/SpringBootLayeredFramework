package com.efreight.common.constants;

/**
 * CRM服务返回状态码
 *
 * @author 张永伟
 * @since 2023/7/14
 */
public class BaseResultCode extends ResultCode {

    public static final BaseResultCode BASE_DATA_IS_NULL = new BaseResultCode(170001, "数据不存在");
    public static final BaseResultCode BASE_FOLDER_FILE_IS_NOT_NULL = new BaseResultCode(170002, "文件夹下存在文件无法删除");
    public static final BaseResultCode BASE_FOLDER_IS_EXIST = new BaseResultCode(170003, "同一个订单类型的文件夹已存在");
    public static final BaseResultCode BASE_FOLDER_IS_NOT_FOUND = new BaseResultCode(170004, "文件夹不存在");
    public static final BaseResultCode BASE_CHARGE_PROJECT_PARENT_IS_NULL = new BaseResultCode(170005, "费用项目只允许添加子费用项目");
    public static final BaseResultCode BASE_FORMULA_IS_NOT_VALIDATE = new BaseResultCode(170006, "公式未验证通过无法保存");
    public static final BaseResultCode BASE_FORMULA_RUNNER_ERROR = new BaseResultCode(170007, "公式编写有误请检查语法");
    public static final BaseResultCode BASE_CHARGE_PROJECT_CTS_ID_IS_EXIST = new BaseResultCode(170008, "新增部分数据已经存在，请调用更新接口");
    public static final BaseResultCode BASE_GLOBAL_CATEGORY_IS_EXIST = new BaseResultCode(170009, "全局分类不允许重复");
    public static final BaseResultCode BASE_GLOBAL_PARAMS_IS_EXIST = new BaseResultCode(170026, "全局参数不允许重复");
    public static final BaseResultCode BASE_GLOBAL_MATE_DATA_ERROR = new BaseResultCode(170031, "全局参数匹配失败，请校验参数名或者参数是否正确");
    public static final BaseResultCode BASE_GLOBAL_VIEW_ONLY_ORDER_TYPE = new BaseResultCode(170032, "全局参数只支持同一订单类型执行");
    public static final BaseResultCode BASE_FORMULA_NUMBER_FORMAT_ERROR = new BaseResultCode(170043, "费用公式结算对象参数有误");
    public static final BaseResultCode BASE_ORDER_TYPE_IS_NULL = new BaseResultCode(170042, "必须选择订单类型");
    public static final BaseResultCode BASE_FORMULA_NAME_IS_EXSIT = new BaseResultCode(170044, "费用公式名称重复");
    
    public BaseResultCode(Integer code, String msg) {
        super(code, msg);
    }
}

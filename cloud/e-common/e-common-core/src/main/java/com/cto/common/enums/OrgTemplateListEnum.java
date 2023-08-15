package com.cto.common.enums;

/**
 * 公司个性模板枚举参数说明
 *
 * @author caiwd
 */
public enum OrgTemplateListEnum {

    /**
     * 结算清单费用
     */
    AE_STATEMENT_LIST_FEE("ae_statement_list_fee", "结算清单(费用)"), AI_STATEMENT_LIST_FEE("ai_statement_list_fee",
            "结算清单(费用)"), VL_VENICLE_DEPART_LIST("vl_vehicle_depart_list", "物流发车单(车单)"),

    AF_SECURITY_LIST("af_security_list", "安检申报单");

    private String code;

    private String name;

    OrgTemplateListEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}

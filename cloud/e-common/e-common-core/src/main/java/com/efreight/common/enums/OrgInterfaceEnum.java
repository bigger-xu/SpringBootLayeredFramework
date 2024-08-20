package com.efreight.common.enums;

import lombok.Getter;

/**
 * 发送报文接口类型枚举类
 *
 * @author Shi Hongkai
 * @since 2023/11/07
 */
@Getter
public enum OrgInterfaceEnum {
    //货站预申报/预录入
    PRE("EAWB_PRE"),
    //运单发送
    FWB_SEND("EAWB_SAVE"),
    //预配预配申报
    FFM_SEND("Mft2201_Decleare"),
    //预配预配强发
    FFM_SEND_FORFORCE("Mft2201_Decleare_ForForce"),
    //预配预配修改
    FFM_SEND_UPDATE("Mft2201_Update"),
    //预配预配删除
    FFM_SEND_DELETE("Mft2201_Delete"),

    //北京主单预申报(ccsp)
    MAWB_PRE("sendMAWBPreEntry"),
    //北京主单预申报(ccsp)
    HAWB_PRE("sendHAWBPreEntry"),

    FWB_MAWB_EZYCARGO(""),
    FWB_HAWB_EZYCARGO(""),

    FWB_MAWB_CCN(""),
    FWB_HAWB_CCN(""),

    FWB_MAWB_CCSP("saveFWBOfIATAXml"),
    FWB_HAWB_CCSP("SendFHLOfCcspXml"),

    FWB_MAWB_TANGE(""),
    FWB_HAWB_TANGE(""),

    //无锡货站预配舱单-主单
    WMS_SEND_MAWB_WX("preMawb"),
    //无锡货站预配舱单-分单
    WMS_SEND_HAWB_WX("preHawb"),
    ;

    private String value;

    OrgInterfaceEnum(String value){
        this.value = value;
    }

}

package com.efreight.hrs.model.web.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * @author 马玉龙
 * @since 2023/8/22
 */
@Data
public class MenuTreeVO extends TreeVO implements Serializable {

    private String icon;
    private String name;
    private String path;
    private String url;
    private String code;
    private Integer sort;
    private String permission;
    private String pageCode;
    private String pageName;
    private String pageModule;
    private String permissionType;
}

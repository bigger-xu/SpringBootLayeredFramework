package com.efreight.hrs.model.web.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author 马玉龙
 * @since 2023/8/22
 */
@Data
public class TreeVO implements Serializable {

    protected Long id;
    protected Long parentId;
    protected List<TreeVO> children;

}

package com.cto.freemarker.entity.base;


import lombok.Data;

import java.io.Serializable;

/**
 * @author Bigger-Xu
 * @version v1.0.1
 * @date 2020/9/20 14:34
 */
@Data
public class BaseQuery implements Serializable {
    private Integer pageNum;
    private Integer pageSize = 10;
}

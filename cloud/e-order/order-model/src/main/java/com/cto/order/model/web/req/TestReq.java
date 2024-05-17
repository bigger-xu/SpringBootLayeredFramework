package com.cto.order.model.web.req;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * </p>
 *
 * @author Zhang Yongwei
 * @since 2023-07-04
 */
@Getter
@Setter
public class TestReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nameEn;

    private String name;
}

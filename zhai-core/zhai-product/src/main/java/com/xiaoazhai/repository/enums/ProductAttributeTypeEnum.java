package com.xiaoazhai.repository.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author jiangyun
 * @date 2021/10/31  19:34
 **/
@AllArgsConstructor
@Getter
public enum ProductAttributeTypeEnum {

    SPECIFICATION(0, "规格"),
    PARAMETER(1, "参数"),
    ;

    private int code;

    private String msg;
}

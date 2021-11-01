package com.xiaoazhai.repository.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author jiangyun
 * @date 2021/10/31  19:36
 **/
@Getter
@AllArgsConstructor
public enum ProductAttributeHandAddStatusEnum {
    UN_SUPPORT(0, "不支持"),
    SUPPORT(1, "从列表中选取"),
    ;

    private int code;

    private String msg;

}

package com.xiaoazhai.repository.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author jiangyun
 * @date 2021/10/31  19:30
 **/
@AllArgsConstructor
@Getter
public enum ProductAttributeInputTypeEnum {

    MANUAL_INPUT(0, "手工输入"),
    SELECT_FROM_LIST(1, "从列表中选取"),
    ;

    private int code;

    private String msg;
}

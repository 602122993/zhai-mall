package com.xiaoazhai.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author jiangyun
 * @date 2021/9/19  21:26
 **/
@AllArgsConstructor
@Getter
public enum CommonStatusEnum {
    USED(1, "可用"),
    UN_USED(0, "不可用");

    private int code;

    private String msg;
}

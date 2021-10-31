package com.xiaoazhai.repository.enums;

import com.xiaoazhai.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author jiangyun
 * @date 2021/10/22  20:25
 **/
@Getter
@AllArgsConstructor
public enum  ProductCategoryLevelEnum implements BaseEnum {

    ONE_LEVEL(0,"一级目录"),
    TWO_LEVEL(1,"二级目录"),
    THREE_LEVEL(2,"三级目录");


    private int code;

    private String msg;


}

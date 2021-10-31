package com.xiaoazhai.enums;

/**
 * @author jiangyun
 * @date 2021/10/22  20:34
 **/
public interface BaseEnum {



    default int getCode() {
        return 0;
    }


    String getMsg();

    default String getKey() {
        return "";
    }

}

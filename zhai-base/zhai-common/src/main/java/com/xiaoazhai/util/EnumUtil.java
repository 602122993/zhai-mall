package com.xiaoazhai.util;

import com.xiaoazhai.enums.BaseEnum;
import com.xiaoazhai.enums.CommonStatusEnum;

import java.util.Objects;

/**
 * @author jiangyun
 * @date 2021/10/22  20:43
 **/
public class EnumUtil {


    public static BaseEnum getByCode(BaseEnum[] baseEnums, int code) {
        for (BaseEnum value : baseEnums) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

    public static BaseEnum getByKey(BaseEnum[] baseEnums, String key) {
        for (BaseEnum value : baseEnums) {
            if (Objects.equals(key, value.getMsg())) {
                return value;
            }
        }
        return null;
    }
}

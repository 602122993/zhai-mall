package com.xiaoazhai.entity;

import com.xiaoazhai.util.BeanUtil;
import com.xiaoazhai.util.GenericsUtils;

/**
 * @author jiangyun
 * @date 2021/9/10  22:50
 **/
public interface BaseEntity<T> {

    default T generalDO(Class<T> clazz) {
        return BeanUtil.copyPropertiesIgnoreNullValue(this, clazz);
    }

    T generalDO();

}

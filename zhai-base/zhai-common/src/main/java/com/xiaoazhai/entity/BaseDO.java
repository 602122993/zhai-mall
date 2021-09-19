package com.xiaoazhai.entity;

import com.xiaoazhai.util.BeanUtil;

/**
 * @author jiangyun
 * @date 2021/9/19  21:05
 **/
public interface BaseDO<T> {

    default T generalEntity(Class<T> clazz) {
        return BeanUtil.copyPropertiesIgnoreNullValue(this, clazz);
    }

    T generalEntity();
}

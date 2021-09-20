package com.xiaoazhai.entity;

import com.xiaoazhai.util.BeanUtil;

/**
 * @author jiangyun
 * @date 2021/9/10  22:50
 **/
public interface BaseEntity<T> {

    default T  generateDO(Class<T> clazz) {
        return BeanUtil.copyPropertiesIgnoreNullValue(this, clazz);
    }

    T  generateDO();

}

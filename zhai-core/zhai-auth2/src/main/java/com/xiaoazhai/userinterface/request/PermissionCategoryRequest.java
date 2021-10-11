package com.xiaoazhai.userinterface.request;

import com.xiaoazhai.domain.entity.PermissionCategoryEntity;
import com.xiaoazhai.util.BeanUtil;
import lombok.Data;

/**
 * @author jiangyun
 * @date 2021/10/9  17:41
 **/
@Data
public class PermissionCategoryRequest {

    private Long id;

    private String name;


    public PermissionCategoryEntity generalEntity() {
        return BeanUtil.copyPropertiesIgnoreNullValue(this, PermissionCategoryEntity.class);
    }
}

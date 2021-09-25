package com.xiaoazhai.userinterface.request;

import com.xiaoazhai.domain.entity.RoleEntity;
import com.xiaoazhai.util.BeanUtil;
import lombok.Data;

/**
 * @author jiangyun
 * @date 2021/9/19  21:21
 **/
@Data
public class RoleRequest {


    private Long id;
    private String name;

    private String description;

    private Integer status;

    private String code;

    public RoleEntity  generateEntity() {
        return BeanUtil.copyPropertiesIgnoreNullValue(this, RoleEntity.class);
    }

}

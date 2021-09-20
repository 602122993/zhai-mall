package com.xiaoazhai.userinterface.request;

import com.xiaoazhai.domain.entity.PermissionEntity;
import lombok.Data;

/**
 * @author jiangyun
 * @date 2021/9/20  12:44
 **/
@Data
public class PermissionRequest {
    private Long id;

    private String name;

    private String uri;

    private String description;

    public PermissionEntity generateEntity() {
        PermissionEntity permissionEntity = new PermissionEntity();
        permissionEntity.setId(id);
        permissionEntity.setName(name);
        permissionEntity.setDescription(description);
        permissionEntity.setUri(uri);
        return permissionEntity;
    }

}

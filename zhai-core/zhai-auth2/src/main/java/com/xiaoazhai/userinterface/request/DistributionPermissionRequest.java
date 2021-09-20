package com.xiaoazhai.userinterface.request;

import com.xiaoazhai.domain.entity.RoleMenuEntity;
import com.xiaoazhai.domain.entity.RolePermissionEntity;
import com.xiaoazhai.repository.entity.RolePermission;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jiangyun
 * @date 2021/9/20  14:01
 **/

@Data
public class DistributionPermissionRequest {

    private Long roleId;

    private List<Long> permissionIdList;

    public List<RolePermissionEntity> generateRolePermissionEntity() {
        return permissionIdList.stream()
                .map(permissionId -> {
                    RolePermissionEntity rolePermissionEntity = new RolePermissionEntity();
                    rolePermissionEntity.setPermissionId(permissionId);
                    rolePermissionEntity.setRoleId(roleId);
                    return rolePermissionEntity;
                })
                .collect(Collectors.toList());
    }
}

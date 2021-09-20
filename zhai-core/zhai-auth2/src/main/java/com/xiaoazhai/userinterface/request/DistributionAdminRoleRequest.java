package com.xiaoazhai.userinterface.request;

import com.xiaoazhai.domain.entity.AdminRoleEntity;
import com.xiaoazhai.repository.entity.AdminRole;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jiangyun
 * @date 2021/9/20  20:52
 **/
@Data
public class DistributionAdminRoleRequest {
    private Long adminId;

    private List<Long> roleIdList;


    public List<AdminRoleEntity> generateAdminRoleEntity() {
        return roleIdList.stream()
                .map(roleId -> {
                    AdminRoleEntity adminRoleEntity = new AdminRoleEntity();
                    adminRoleEntity.setAdminId(adminId);
                    adminRoleEntity.setRoleId(roleId);
                    return adminRoleEntity;
                })
                .collect(Collectors.toList());
    }
}

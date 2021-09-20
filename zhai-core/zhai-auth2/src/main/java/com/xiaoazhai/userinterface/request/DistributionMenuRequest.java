package com.xiaoazhai.userinterface.request;

import com.xiaoazhai.domain.entity.RoleMenuEntity;
import com.xiaoazhai.repository.entity.RoleMenu;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jiangyun
 * @date 2021/9/20  14:01
 **/

@Data
public class DistributionMenuRequest {

    private Long roleId;

    private List<Long> menuIdList;

    public List<RoleMenuEntity> generateRoleMenuEntity() {
        return menuIdList.stream()
                .map(menuId -> {
                    RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
                    roleMenuEntity.setMenuId(menuId);
                    roleMenuEntity.setRoleId(roleId);
                    return roleMenuEntity;
                })
                .collect(Collectors.toList());
    }
}

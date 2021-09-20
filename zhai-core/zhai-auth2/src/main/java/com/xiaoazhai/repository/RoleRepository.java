package com.xiaoazhai.repository;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoazhai.domain.entity.RoleEntity;
import com.xiaoazhai.domain.entity.RoleMenuEntity;
import com.xiaoazhai.domain.entity.RolePermissionEntity;
import com.xiaoazhai.repository.entity.Role;
import com.xiaoazhai.repository.entity.RoleMenu;
import com.xiaoazhai.repository.entity.RolePermission;
import com.xiaoazhai.repository.service.RoleMenuService;
import com.xiaoazhai.repository.service.RolePermissionService;
import com.xiaoazhai.repository.service.RoleService;
import com.xiaoazhai.util.BeanUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jiangyun
 * @date 2021/9/19  14:25
 **/
@Component
public class RoleRepository {

    @Resource
    private RoleService roleService;
    @Resource
    private RoleMenuService roleMenuService;
    @Resource
    private RolePermissionService rolePermissionService;

    public IPage<RoleEntity> queryRolePage(Page page, String name) {
        return roleService.queryRolePage(page, name);
    }

    public void saveRole(RoleEntity roleEntity) {
        roleService.saveRole(roleEntity.generateDO(Role.class));
    }

    public void updateRole(RoleEntity roleEntity) {
        roleService.updateRoleById(roleEntity.generateDO(Role.class));
    }

    public void deleteRole(Long id) {
        roleService.removeRoleById(id);
    }

    public RoleEntity queryById(Long id) {
        return BeanUtil.doToEntity(roleService.queryRoleById(id), RoleEntity.class);
    }

    @Transactional(rollbackFor = Exception.class)
    public void distributionMenu(Long roleId, List<RoleMenuEntity> roleMenuEntityList) {
        roleMenuService.removeByRoleId(roleId);
        if (CollectionUtil.isNotEmpty(roleMenuEntityList)) {
            roleMenuService.saveBatch(BeanUtil.entityToDOBatch(roleMenuEntityList, RoleMenu.class));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void distributionPermission(Long roleId, List<RolePermissionEntity> rolePermissionEntityList) {
        rolePermissionService.removeByRoleId(roleId);
        if (CollectionUtil.isNotEmpty(rolePermissionEntityList)) {
            rolePermissionService.saveBatch(BeanUtil.entityToDOBatch(rolePermissionEntityList, RolePermission.class));
        }
    }
}

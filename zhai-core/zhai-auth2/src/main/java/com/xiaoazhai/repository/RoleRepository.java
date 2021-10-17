package com.xiaoazhai.repository;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoazhai.domain.entity.RoleEntity;
import com.xiaoazhai.domain.entity.RoleMenuEntity;
import com.xiaoazhai.domain.entity.RolePermissionEntity;
import com.xiaoazhai.repository.entity.AdminRole;
import com.xiaoazhai.repository.entity.Role;
import com.xiaoazhai.repository.entity.RoleMenu;
import com.xiaoazhai.repository.entity.RolePermission;
import com.xiaoazhai.repository.service.AdminRoleService;
import com.xiaoazhai.repository.service.RoleMenuService;
import com.xiaoazhai.repository.service.RolePermissionService;
import com.xiaoazhai.repository.service.RoleService;
import com.xiaoazhai.util.BeanUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Resource
    private AdminRoleService adminRoleService;

    public IPage<RoleEntity> queryRolePage(Page page, String name) {
        return roleService.queryRolePage(page, name);
    }

    public void saveRole(RoleEntity roleEntity) {
        this.checkRepeatRoleCode(roleEntity);
        roleService.saveRole(roleEntity.generateDO(Role.class));
    }

    private void checkRepeatRoleCode(RoleEntity code) {
        Assert.isNull(this.roleService.queryRoleByCodeAndId(code.getCode(), code.getId()), "角色代码冲突！");
    }

    private RoleEntity queryRoleByCode(String code) {
        return roleService.queryRoleByCode(code);
    }

    public void updateRole(RoleEntity roleEntity) {
        checkRepeatRoleCode(roleEntity);
        roleService.updateRoleById(roleEntity.generateDO(Role.class));
    }

    public void deleteRole(Long id) {
        roleService.removeRoleById(id);
    }

    public RoleEntity queryById(Long id) {
        return roleService.queryRoleById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void distributionMenu(Long roleId, List<RoleMenuEntity> roleMenuEntityList) {
        roleMenuService.removeByRoleId(roleId);
        if (CollectionUtil.isNotEmpty(roleMenuEntityList)) {
            roleMenuService.saveBatch(BeanUtil.entityToDOBatch(roleMenuEntityList ));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void distributionPermission(Long roleId, List<RolePermissionEntity> rolePermissionEntityList) {
        rolePermissionService.removeByRoleId(roleId);
        if (CollectionUtil.isNotEmpty(rolePermissionEntityList)) {
            rolePermissionService.saveBatch(BeanUtil.entityToDOBatch(rolePermissionEntityList ));
        }
    }

    public List<RoleEntity> queryByAdminId(Long adminId) {
        List<AdminRole> adminRoleList = adminRoleService.queryByAdminId(adminId);
        List<Long> roleIdList = adminRoleList.stream()
                .map(AdminRole::getRoleId)
                .collect(Collectors.toList());
        if (CollectionUtil.isEmpty(roleIdList)) {
            return new ArrayList<>();
        }
        List<Role> roleList = roleService.listByIds(roleIdList);
        return BeanUtil.doToEntityBatch(roleList);
    }

    public List<RoleEntity> queryAllRoleListByMenuId(Long id) {
        List<Long> roleIdList = roleMenuService.queryRoleIdListByMenuId(id);
        if (CollectionUtil.isEmpty(roleIdList)) {
            return new ArrayList<>();
        }
        return roleService.queryListByIds(roleIdList);
    }

    public List<RoleEntity> queryAllRole() {
        return roleService.queryAllRole();
    }
}

package com.xiaoazhai.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoazhai.domain.entity.PermissionCategoryEntity;
import com.xiaoazhai.domain.entity.PermissionEntity;
import com.xiaoazhai.handler.FillFieldHandler;
import com.xiaoazhai.repository.entity.PermissionCategory;
import com.xiaoazhai.repository.service.PermissionCategoryService;
import com.xiaoazhai.repository.service.PermissionService;
import com.xiaoazhai.repository.service.RolePermissionService;
import com.xiaoazhai.util.BeanUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jiangyun
 * @date 2021/9/20  12:19
 **/
@Component
public class PermissionRepository {

    @Resource
    private PermissionService permissionService;

    @Resource
    private FillFieldHandler fillFieldHandler;
    @Resource
    private RolePermissionService rolePermissionService;

    @Resource
    private PermissionCategoryService permissionCategoryService;

    public IPage<PermissionEntity> queryPermissionPage(Page page, String name) {
        IPage<PermissionEntity> result = BeanUtil.copyPage(permissionService.queryPermissionPage(page, name) );
        fillFieldHandler.fillPermissionCategoryName(PermissionEntity::getCategoryId, PermissionEntity::setCategoryName, result.getRecords());
        return result;
    }

    public void savePermission(PermissionEntity generateEntity) {
        permissionService.savePermission(generateEntity.generateDO());
    }

    public void updatePermissionById(PermissionEntity generateEntity) {
        permissionService.updatePermission(generateEntity.generateDO());
    }

    public PermissionEntity queryPermissionById(Long id) {
        return BeanUtil.doToEntity(permissionService.queryPermissionById(id) );
    }

    public void deleteById(Long id) {
        permissionService.removeById(id);
    }

    public List<Long> queryPermissionByRoleId(Long roleId) {
        return rolePermissionService.queryPermissionIdListByRoleId(roleId);
    }

    public List<PermissionCategoryEntity> queryPermissionTreeList() {
        List<PermissionEntity> permissionEntityList = permissionService.queryAllPermission();
        List<PermissionCategoryEntity> permissionCategoryList = permissionCategoryService.queryList();
        permissionCategoryList.forEach(permissionCategoryEntity -> {
            permissionCategoryEntity.formatPermissionList(permissionEntityList);
        });
        return permissionCategoryList;
    }

    public List<PermissionCategoryEntity> queryPermissionCategoryList() {
        return permissionCategoryService.queryList();
    }

    public void savePermissionCategory(PermissionCategoryEntity permissionCategoryEntity) {
        permissionCategoryService.savePermissionCategory(permissionCategoryEntity);
    }

    public void updatePermissionCategoryById(PermissionCategoryEntity generalEntity) {
        permissionCategoryService.updatePermissionCategory(generalEntity);
    }

    public void removePermissionCategoryById(Long id) {
        permissionCategoryService.removeById(id);
    }
}

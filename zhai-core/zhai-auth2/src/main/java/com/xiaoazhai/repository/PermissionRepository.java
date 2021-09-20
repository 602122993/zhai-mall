package com.xiaoazhai.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoazhai.domain.entity.PermissionEntity;
import com.xiaoazhai.repository.service.PermissionService;
import com.xiaoazhai.util.BeanUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author jiangyun
 * @date 2021/9/20  12:19
 **/
@Component
public class PermissionRepository {

    @Resource
    private PermissionService permissionService;

    public IPage<PermissionEntity> queryPermissionPage(Page page, String name) {
        return BeanUtil.copyPage(permissionService.queryPermissionPage(page, name), PermissionEntity.class);
    }

    public void savePermission(PermissionEntity generateEntity) {
        permissionService.savePermission(generateEntity.generateDO());
    }

    public void updatePermissionById(PermissionEntity generateEntity) {
        permissionService.updatePermission(generateEntity.generateDO());
    }

    public PermissionEntity queryPermissionById(Long id) {
        return BeanUtil.doToEntity(permissionService.queryPermissionById(id), PermissionEntity.class);
    }

    public void deleteById(Long id) {
        permissionService.removeById(id);
    }
}

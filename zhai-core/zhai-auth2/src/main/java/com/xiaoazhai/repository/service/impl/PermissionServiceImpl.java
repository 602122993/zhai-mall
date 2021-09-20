package com.xiaoazhai.repository.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoazhai.repository.entity.Permission;
import com.xiaoazhai.repository.mapper.PermissionMapper;
import com.xiaoazhai.repository.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhai
 * @since 2021-09-19
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public IPage<Permission> queryPermissionPage(Page page, String name) {
        return this.page(page, Wrappers.<Permission>lambdaQuery()
                .like(Permission::getName, name));
    }

    @Override
    public void savePermission(Permission permission) {
        this.save(permission);
    }

    @Override
    public void updatePermission(Permission permission) {
        this.updateById(permission);
    }

    @Override
    public Permission queryPermissionById(Long id) {
        return getById(id);
    }
}

package com.xiaoazhai.repository.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoazhai.repository.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhai
 * @since 2021-09-19
 */
public interface PermissionService extends IService<Permission> {

    IPage<Permission> queryPermissionPage(Page page, String name);

    void savePermission(Permission generateDO);

    void updatePermission(Permission generateDO);

    Permission queryPermissionById(Long id);
}

package com.xiaoazhai.repository.service;

import com.xiaoazhai.repository.entity.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhai
 * @since 2021-09-19
 */
public interface RolePermissionService extends IService<RolePermission> {

    void removeByRoleId(Long roleId);
}

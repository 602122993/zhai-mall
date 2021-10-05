package com.xiaoazhai.repository.service;

import com.xiaoazhai.domain.entity.PermissionEntity;
import com.xiaoazhai.repository.entity.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    List<Long> queryPermissionIdListByRoleId(Long roleId);

}

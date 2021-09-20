package com.xiaoazhai.repository.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiaoazhai.repository.entity.RolePermission;
import com.xiaoazhai.repository.mapper.RolePermissionMapper;
import com.xiaoazhai.repository.service.RolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhai
 * @since 2021-09-19
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Override
    public void removeByRoleId(Long roleId) {
        this.remove(Wrappers.<RolePermission>lambdaQuery()
                .eq(RolePermission::getRoleId, roleId));
    }
}

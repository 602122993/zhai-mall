package com.xiaoazhai.repository.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiaoazhai.domain.entity.PermissionEntity;
import com.xiaoazhai.repository.entity.RolePermission;
import com.xiaoazhai.repository.mapper.RolePermissionMapper;
import com.xiaoazhai.repository.service.RolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoazhai.util.BeanUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Long> queryPermissionIdListByRoleId(Long roleId) {
        return this.list(Wrappers.<RolePermission>lambdaQuery()
                .eq(RolePermission::getRoleId, roleId))
                .stream()
                .map(RolePermission::getPermissionId)
                .collect(Collectors.toList());
    }


}

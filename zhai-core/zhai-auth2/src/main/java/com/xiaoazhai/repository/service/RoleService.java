package com.xiaoazhai.repository.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoazhai.domain.entity.RoleEntity;
import com.xiaoazhai.repository.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhai
 * @since 2021-09-19
 */
public interface RoleService extends IService<Role> {

    IPage<RoleEntity> queryRolePage(Page<Role> page, String name);

    void saveRole(Role generateDO);

    void updateRoleById(Role generateDO);

    void removeRoleById(Long id);

    Role queryRoleById(Long id);
}

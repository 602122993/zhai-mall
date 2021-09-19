package com.xiaoazhai.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoazhai.domain.entity.RoleEntity;
import com.xiaoazhai.repository.entity.Role;
import com.xiaoazhai.repository.service.RoleService;
import com.xiaoazhai.util.BeanUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author jiangyun
 * @date 2021/9/19  14:25
 **/
@Component
public class RoleRepository {

    @Resource
    private RoleService roleService;

    public IPage<RoleEntity> queryRolePage(Page page, String name) {
        return roleService.queryRolePage(page, name);
    }

    public void saveRole(RoleEntity roleEntity) {
        roleService.save(roleEntity.generalDO(Role.class));
    }

    public void updateRole(RoleEntity roleEntity) {
        roleService.updateById(roleEntity.generalDO(Role.class));
    }

    public void deleteRole(Long id) {
        roleService.removeById(id);
    }

    public RoleEntity queryById(Long id) {
        return BeanUtil.doToEntity(roleService.getById(id), RoleEntity.class);
    }
}

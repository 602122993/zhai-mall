package com.xiaoazhai.repository.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoazhai.domain.entity.RoleEntity;
import com.xiaoazhai.repository.entity.Role;
import com.xiaoazhai.repository.mapper.RoleMapper;
import com.xiaoazhai.repository.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoazhai.util.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhai
 * @since 2021-09-19
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public IPage<RoleEntity> queryRolePage(Page page, String name) {
        return BeanUtil.copyPage(this.page(page, Wrappers.<Role>lambdaQuery()
                .orderByDesc(Role::getId)
                .like(StringUtils.isNotEmpty(name), Role::getName, name)), RoleEntity.class);
    }

    @Override
    public void saveRole(Role generateDO) {
        save(generateDO);
    }

    @Override
    public void updateRoleById(Role generateDO) {
        updateById(generateDO);
    }

    @Override
    public void removeRoleById(Long id) {
        removeById(id);
    }

    @Override
    public RoleEntity queryRoleById(Long id) {
        return BeanUtil.doToEntity(this.getById(id), RoleEntity.class);
    }

    @Override
    public List<RoleEntity> queryListByIds(List<Long> roleIdList) {
        return BeanUtil.doToEntityBatch(this.listByIds(roleIdList), RoleEntity.class);
    }

    @Override
    public RoleEntity queryRoleByCode(String code) {
        return BeanUtil.doToEntity(this.getOne(Wrappers.<Role>lambdaQuery()
                .eq(Role::getCode, code)), RoleEntity.class);
    }

    @Override
    public RoleEntity queryRoleByCodeAndId(String code, Long id) {
        return BeanUtil.doToEntity(this.getOne(Wrappers.<Role>lambdaQuery()
                .eq(Role::getCode, code)
                .ne(id != null, Role::getId, id)), RoleEntity.class);
    }
}

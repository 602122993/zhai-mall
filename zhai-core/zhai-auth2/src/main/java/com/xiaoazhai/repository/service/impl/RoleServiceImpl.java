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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public IPage<RoleEntity> queryRolePage(Page page, String name) {
        return BeanUtil.copyPage(this.page(page, Wrappers.<Role>lambdaQuery()
                .like(Role::getName, name)), RoleEntity.class);
    }
}

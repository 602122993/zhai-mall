package com.xiaoazhai.repository.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiaoazhai.repository.entity.RoleMenu;
import com.xiaoazhai.repository.mapper.RoleMenuMapper;
import com.xiaoazhai.repository.service.RoleMenuService;
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
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Override
    public void removeByRoleId(Long roleId) {
        this.remove(Wrappers.<RoleMenu>lambdaQuery().eq(RoleMenu::getRoleId, roleId));
    }
}

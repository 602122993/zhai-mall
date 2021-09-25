package com.xiaoazhai.repository.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiaoazhai.domain.entity.RoleMenuEntity;
import com.xiaoazhai.repository.entity.RoleMenu;
import com.xiaoazhai.repository.mapper.RoleMenuMapper;
import com.xiaoazhai.repository.service.RoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoazhai.util.BeanUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Long> queryRoleIdListByMenuId(Long id) {
        return this.list(Wrappers.<RoleMenu>lambdaQuery()
                .eq(RoleMenu::getMenuId, id))
                .stream()
                .map(RoleMenu::getRoleId)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoleMenuEntity> queryListByRoleIdList(List<Long> roleIdList) {
        return BeanUtil.doToEntityBatch(this.list(Wrappers.<RoleMenu>lambdaQuery()
                .in(RoleMenu::getRoleId, roleIdList)), RoleMenuEntity.class);
    }

}

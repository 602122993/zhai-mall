package com.xiaoazhai.repository.service;

import com.xiaoazhai.domain.entity.MenuEntity;
import com.xiaoazhai.domain.entity.RoleMenuEntity;
import com.xiaoazhai.repository.entity.RoleMenu;
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
public interface RoleMenuService extends IService<RoleMenu> {

    void removeByRoleId(Long roleId);

    List<Long> queryRoleIdListByMenuId(Long id);

    List<RoleMenuEntity> queryListByRoleIdList(List<Long> roleIdList);

    List<Long> queryMenuIdListByRoleId(String roleId);


}

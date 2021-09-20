package com.xiaoazhai.repository.service;

import com.xiaoazhai.repository.entity.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

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
}

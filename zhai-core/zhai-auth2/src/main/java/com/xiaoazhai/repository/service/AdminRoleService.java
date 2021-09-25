package com.xiaoazhai.repository.service;

import com.xiaoazhai.domain.entity.RoleEntity;
import com.xiaoazhai.repository.entity.AdminRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoazhai.repository.entity.Role;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhai
 * @since 2021-09-20
 */
public interface AdminRoleService extends IService<AdminRole> {

    void removeByAdminId(Long id);

    List<AdminRole> queryByAdminIdList(List<Long> adminIdList);

    List<AdminRole> queryByAdminId(Long id);
}

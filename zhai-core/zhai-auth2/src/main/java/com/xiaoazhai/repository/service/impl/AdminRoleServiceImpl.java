package com.xiaoazhai.repository.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiaoazhai.repository.entity.AdminRole;
import com.xiaoazhai.repository.mapper.AdminRoleMapper;
import com.xiaoazhai.repository.service.AdminRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhai
 * @since 2021-09-20
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {

    @Override
    public void removeByAdminId(Long adminId) {
        this.remove(Wrappers.<AdminRole>lambdaQuery()
                .eq(AdminRole::getAdminId, adminId));
    }

    @Override
    public List<AdminRole> queryByAdminIdList(List<Long> adminIdList) {
        return this.list(Wrappers.<AdminRole>lambdaQuery()
                .in(AdminRole::getAdminId, adminIdList));
    }

    @Override
    public List<AdminRole> queryByAdminId(Long adminId) {
        return this.list(Wrappers.<AdminRole>lambdaQuery()
                .eq(AdminRole::getAdminId, adminId));
    }
}

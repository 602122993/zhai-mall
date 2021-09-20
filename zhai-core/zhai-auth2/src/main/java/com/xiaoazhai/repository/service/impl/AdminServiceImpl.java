package com.xiaoazhai.repository.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoazhai.domain.entity.AdminEntity;
import com.xiaoazhai.repository.entity.Admin;
import com.xiaoazhai.repository.mapper.AdminMapper;
import com.xiaoazhai.repository.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoazhai.util.BeanUtil;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhai
 * @since 2021-09-20
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Override
    public IPage<AdminEntity> queryAdminList(Page page, String name) {
        return BeanUtil.copyPage(this.page(page, Wrappers.<Admin>lambdaQuery().like(Admin::getName, name)), AdminEntity.class);
    }
}

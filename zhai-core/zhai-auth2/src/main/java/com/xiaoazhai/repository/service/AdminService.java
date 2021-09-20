package com.xiaoazhai.repository.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoazhai.domain.entity.AdminEntity;
import com.xiaoazhai.repository.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhai
 * @since 2021-09-20
 */
public interface AdminService extends IService<Admin> {

    IPage<AdminEntity> queryAdminList(Page page, String name);
}

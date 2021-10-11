package com.xiaoazhai.repository.service;

import com.xiaoazhai.domain.entity.PermissionCategoryEntity;
import com.xiaoazhai.repository.entity.PermissionCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhai
 * @since 2021-10-04
 */
public interface PermissionCategoryService extends IService<PermissionCategory> {

    List<PermissionCategoryEntity> queryList();

    void savePermissionCategory(PermissionCategoryEntity permissionCategoryEntity);

    void updatePermissionCategory(PermissionCategoryEntity generalEntity);
}

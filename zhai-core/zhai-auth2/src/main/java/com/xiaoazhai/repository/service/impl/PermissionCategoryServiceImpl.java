package com.xiaoazhai.repository.service.impl;

import com.xiaoazhai.domain.entity.PermissionCategoryEntity;
import com.xiaoazhai.repository.entity.PermissionCategory;
import com.xiaoazhai.repository.mapper.PermissionCategoryMapper;
import com.xiaoazhai.repository.service.PermissionCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoazhai.util.BeanUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhai
 * @since 2021-10-04
 */
@Service
public class PermissionCategoryServiceImpl extends ServiceImpl<PermissionCategoryMapper, PermissionCategory> implements PermissionCategoryService {

    @Override
    public List<PermissionCategoryEntity> queryList() {
        return BeanUtil.doToEntityBatch(this.list() );
    }

    @Override
    public void savePermissionCategory(PermissionCategoryEntity permissionCategoryEntity) {
        this.save(permissionCategoryEntity.generateDO());
    }

    @Override
    public void updatePermissionCategory(PermissionCategoryEntity generalEntity) {
        this.updateById(generalEntity.generateDO());
    }
}

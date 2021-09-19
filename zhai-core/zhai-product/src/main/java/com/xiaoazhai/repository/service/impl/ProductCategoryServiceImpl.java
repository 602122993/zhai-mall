package com.xiaoazhai.repository.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoazhai.repository.entity.ProductCategory;
import com.xiaoazhai.repository.mapper.ProductCategoryMapper;
import com.xiaoazhai.repository.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author zhai
 * @since 2021-09-05
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Override
    public IPage<ProductCategory> listByParentId(Page<ProductCategory> page, String parentId) {
        return this.page(page, Wrappers.<ProductCategory>lambdaQuery()
                .eq(StringUtils.isNotBlank(parentId),ProductCategory::getParentId, parentId));
    }
}

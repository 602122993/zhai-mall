package com.xiaoazhai.repository.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoazhai.repository.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author zhai
 * @since 2021-09-05
 */
public interface ProductCategoryService extends IService<ProductCategory> {

    IPage<ProductCategory> listByParentId(Page<ProductCategory> page, String id);
}

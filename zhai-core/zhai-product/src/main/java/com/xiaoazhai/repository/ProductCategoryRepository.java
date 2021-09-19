package com.xiaoazhai.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoazhai.domain.entity.ProductCategoryEntity;
import com.xiaoazhai.repository.entity.ProductCategory;
import com.xiaoazhai.repository.service.ProductCategoryService;
import com.xiaoazhai.userinterface.request.QueryProductCategoryForm;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author jiangyun
 * @date 2021/9/5  12:19
 **/
@Component
public class ProductCategoryRepository {


    @Resource
    private ProductCategoryService productCategoryService;


    public void saveProductCategory(ProductCategoryEntity generalEntity) {
        productCategoryService.save(generalEntity.generalDO());
    }

    public IPage<ProductCategory> queryProductCategory(Page<ProductCategory> page, String id) {
        return productCategoryService.listByParentId(page, id);
    }


}

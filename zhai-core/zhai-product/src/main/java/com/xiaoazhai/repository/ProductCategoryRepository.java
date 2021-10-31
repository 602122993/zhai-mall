package com.xiaoazhai.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoazhai.domain.entity.ProductCategoryEntity;
import com.xiaoazhai.repository.entity.ProductCategory;
import com.xiaoazhai.repository.service.ProductCategoryService;
import com.xiaoazhai.userinterface.request.QueryProductCategoryForm;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jiangyun
 * @date 2021/9/5  12:19
 **/
@Component
public class ProductCategoryRepository {


    @Resource
    private ProductCategoryService productCategoryService;


    public void saveProductCategory(ProductCategoryEntity generateEntity) {
        productCategoryService.save(generateEntity.generateDO());
    }

    public IPage<ProductCategoryEntity> queryProductCategory(Page<ProductCategory> page, String id) {
        return productCategoryService.listByParentId(page, id);
    }


    public List<ProductCategoryEntity> queryParentProductCategoryList() {
        return productCategoryService.queryParentProductCategoryList();
    }

    public ProductCategoryEntity queryById(Long id) {
        return productCategoryService.queryById(id);
    }

    public void updateProductCategory(ProductCategoryEntity generateEntity) {
        productCategoryService.updateById(generateEntity.generateDO());
    }

    public List<ProductCategoryEntity> queryAppProductCategoryList() {
        return productCategoryService.queryAppProductCategoryList();
    }

    public void updateProductCategoryList(List<ProductCategoryEntity> productCategoryEntityList) {
        productCategoryService.updateBatchById(productCategoryEntityList);
    }

    public void removeProductCategory(Long id) {
        productCategoryService.removeById(id);
    }
}

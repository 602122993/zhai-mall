package com.xiaoazhai.repository.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoazhai.domain.entity.ProductCategoryEntity;
import com.xiaoazhai.repository.entity.ProductCategory;
import com.xiaoazhai.repository.mapper.ProductCategoryMapper;
import com.xiaoazhai.repository.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoazhai.util.BeanUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public static final Long PARENT_CATEGORY_ID = 0L;

    @Override
    public IPage<ProductCategoryEntity> listByParentId(Page<ProductCategory> page, String parentId) {
        Page<ProductCategory> result = this.page(page, Wrappers.<ProductCategory>lambdaQuery()
                .orderByAsc(ProductCategory::getSort)
                .eq(StringUtils.isNotBlank(parentId), ProductCategory::getParentId, parentId));
        return BeanUtil.copyPage(result);
    }

    @Override
    public List<ProductCategoryEntity> queryParentProductCategoryList() {
        return this.list(Wrappers.<ProductCategory>lambdaQuery()
                .orderByAsc(ProductCategory::getSort)
                .eq(ProductCategory::getParentId, PARENT_CATEGORY_ID))
                .stream()
                .map(ProductCategory::generateEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ProductCategoryEntity queryById(Long id) {
        return BeanUtil.doToEntity(this.getById(id));
    }

    @Override
    public List<ProductCategoryEntity> queryAppProductCategoryList() {
        List<ProductCategory> list = this.list(Wrappers.<ProductCategory>lambdaQuery()
                .orderByAsc(ProductCategory::getSort)
                .select(ProductCategory::getIcon, ProductCategory::getId, ProductCategory::getName, ProductCategory::getParentId));
        List<ProductCategoryEntity> entityList = BeanUtil.doToEntityBatch(list);
        return entityList.stream()
                .filter(entity -> Objects.equals(entity.getParentId(), PARENT_CATEGORY_ID))
                .peek(entity -> entity.fillChildList(entityList))
                .collect(Collectors.toList());
    }

    @Override
    public void updateBatchById(List<ProductCategoryEntity> productCategoryEntityList) {
        this.updateBatchById(BeanUtil.entityToDOBatch(productCategoryEntityList));
    }
}

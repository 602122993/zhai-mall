package com.xiaoazhai.repository.service.impl;

import com.xiaoazhai.repository.entity.ProductCategoryAttributeRelation;
import com.xiaoazhai.repository.mapper.ProductCategoryAttributeRelationMapper;
import com.xiaoazhai.repository.service.ProductCategoryAttributeRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） 服务实现类
 * </p>
 *
 * @author zhai
 * @since 2021-10-31
 */
@Service
public class ProductCategoryAttributeRelationServiceImpl extends ServiceImpl<ProductCategoryAttributeRelationMapper, ProductCategoryAttributeRelation> implements ProductCategoryAttributeRelationService {

}

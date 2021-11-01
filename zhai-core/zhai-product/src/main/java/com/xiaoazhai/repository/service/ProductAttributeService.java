package com.xiaoazhai.repository.service;

import com.xiaoazhai.domain.entity.ProductAttributeEntity;
import com.xiaoazhai.repository.entity.ProductAttribute;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhai
 * @since 2021-10-31
 */
public interface ProductAttributeService extends IService<ProductAttribute> {

    List<ProductAttributeEntity> queryListByProductCategoryId(Long productCategoryId);
}

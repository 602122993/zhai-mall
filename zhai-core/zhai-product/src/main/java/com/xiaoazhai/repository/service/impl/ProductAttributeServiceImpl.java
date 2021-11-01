package com.xiaoazhai.repository.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiaoazhai.domain.entity.ProductAttributeEntity;
import com.xiaoazhai.repository.entity.ProductAttribute;
import com.xiaoazhai.repository.mapper.ProductAttributeMapper;
import com.xiaoazhai.repository.service.ProductAttributeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhai
 * @since 2021-10-31
 */
@Service
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttribute> implements ProductAttributeService {

    @Override
    public List<ProductAttributeEntity> queryListByProductCategoryId(Long productCategoryId) {
        return null;
    }
}

package com.xiaoazhai.repository;

import com.xiaoazhai.domain.entity.ProductAttributeEntity;
import com.xiaoazhai.repository.service.ProductAttributeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jiangyun
 * @date 2021/10/31  19:45
 **/
@Component
public class ProductAttributeRepository {

    @Resource
    private ProductAttributeService productAttributeService;

    public List<ProductAttributeEntity> queryProductAttributeListByProductCategoryId(Long productCategoryId) {
        return productAttributeService.queryListByProductCategoryId(productCategoryId);
    }
}

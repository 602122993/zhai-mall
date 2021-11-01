package com.xiaoazhai.domain.service;

import com.xiaoazhai.repository.ProductCategoryRepository;
import com.xiaoazhai.repository.entity.ProductAttribute;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jiangyun
 * @date 2021/10/31  13:06
 **/
@Component
public class ProductCategoryDomainService {

    @Resource
    private ProductCategoryRepository productCategoryRepository;

    public void removeProductCategory(Long id) {
        productCategoryRepository.removeProductCategory(id);
    }

    public List<ProductAttribute> queryProductAttributeListByProductCategoryId(Long productCategoryId) {
        return productCategoryRepository.queryById(productCategoryId).getAttributeList();

    }
}

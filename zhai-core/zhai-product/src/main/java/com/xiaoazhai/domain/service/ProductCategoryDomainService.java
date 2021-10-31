package com.xiaoazhai.domain.service;

import com.xiaoazhai.repository.ProductCategoryRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
}

package com.xiaoazhai.userinterface.request;

import com.xiaoazhai.domain.entity.ProductCategoryEntity;
import com.xiaoazhai.util.BeanUtil;
import lombok.Data;

/**
 * @author jiangyun
 * @date 2021/10/30  17:07
 **/
@Data
public class UpdateProductSortRequest {

    private Long id;

    private Integer sort;

    public ProductCategoryEntity generateEntity() {
        return BeanUtil.copyPropertiesIgnoreNullValue(this, ProductCategoryEntity.class);
    }
}

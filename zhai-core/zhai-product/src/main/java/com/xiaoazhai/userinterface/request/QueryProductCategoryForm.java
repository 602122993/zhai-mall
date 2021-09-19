package com.xiaoazhai.userinterface.request;

import com.xiaoazhai.entity.BaseQueryForm;
import com.xiaoazhai.repository.entity.ProductCategory;
import lombok.Data;

/**
 * @author jiangyun
 * @date 2021/9/10  22:24
 **/
@Data
public class QueryProductCategoryForm extends BaseQueryForm<ProductCategory> {

    private String id;
}

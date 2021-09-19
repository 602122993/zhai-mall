package com.xiaoazhai.domain.entity;

import com.xiaoazhai.entity.BaseEntity;
import com.xiaoazhai.repository.entity.ProductCategory;
import lombok.Data;

import java.util.List;

/**
 * @author jiangyun
 * @date 2021/9/5  12:20
 **/
@Data
public class ProductCategoryEntity implements BaseEntity<ProductCategory> {


    private Long id;

    /**
     * 上机分类的编号：0表示一级分类
     */
    private Long parentId;

    private String name;

    /**
     * 分类级别：0->1级；1->2级
     */
    private Integer level;

    private Integer productCount;

    private String productUnit;

    /**
     * 是否显示在导航栏：0->不显示；1->显示
     */
    private Boolean navStatus;

    /**
     * 显示状态：0->不显示；1->显示
     */
    private Boolean showStatus;

    private Integer sort;

    /**
     * 图标
     */
    private String icon;

    private String keywords;

    /**
     * 描述
     */
    private String description;

    private List<Long> attributeIdList;


    @Override
    public ProductCategory generalDO() {
        return generalDO(ProductCategory.class);
    }
}

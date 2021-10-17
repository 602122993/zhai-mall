package com.xiaoazhai.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.xiaoazhai.domain.entity.ProductCategoryEntity;
import com.xiaoazhai.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 产品分类
 * </p>
 *
 * @author zhai
 * @since 2021-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("zhai_product_category")
public class ProductCategory implements Serializable, BaseDO<ProductCategoryEntity> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 上机分类的编号：0表示一级分类
     */
    @TableField("parent_id")
    private Long parentId;

    @TableField("name")
    private String name;

    /**
     * 分类级别：0->1级；1->2级
     */
    @TableField("level")
    private Integer level;

    @TableField("product_count")
    private Integer productCount;

    @TableField("product_unit")
    private String productUnit;

    /**
     * 是否显示在导航栏：0->不显示；1->显示
     */
    @TableField("nav_status")
    private Boolean navStatus;

    /**
     * 显示状态：0->不显示；1->显示
     */
    @TableField("show_status")
    private Boolean showStatus;

    @TableField("sort")
    private Integer sort;

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;

    @TableField("keywords")
    private String keywords;

    /**
     * 描述
     */
    @TableField("description")
    private String description;


    @Override
    public ProductCategoryEntity generateEntity() {
        return generateEntity(ProductCategoryEntity.class);
    }
}

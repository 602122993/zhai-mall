package com.xiaoazhai.userinterface.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xiaoazhai.domain.entity.ProductCategoryEntity;
import com.xiaoazhai.util.BeanUtil;
import lombok.Data;

import java.util.List;

/**
 * @author jiangyun
 * @date 2021/9/5  11:32
 **/
@Data
public class AddProductCategoryRequest {


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

    public ProductCategoryEntity generalEntity() {
        return BeanUtil.copyPropertiesIgnoreNullValue(this, ProductCategoryEntity.class);
    }
}

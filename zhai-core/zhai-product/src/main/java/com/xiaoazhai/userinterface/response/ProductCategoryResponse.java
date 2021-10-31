package com.xiaoazhai.userinterface.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoazhai.domain.entity.ProductCategoryEntity;
import com.xiaoazhai.repository.enums.ProductCategoryLevelEnum;
import com.xiaoazhai.util.BeanUtil;
import com.xiaoazhai.util.EnumUtil;
import lombok.Data;

import java.util.List;

/**
 * @author jiangyun
 * @date 2021/10/22  20:31
 **/
@Data
public class ProductCategoryResponse {

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

    private String levelStr;

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


    public static ProductCategoryResponse generateFromEntity(ProductCategoryEntity entity) {
        ProductCategoryResponse result = BeanUtil.copyPropertiesIgnoreNullValue(entity, ProductCategoryResponse.class);
        result.setLevelStr(EnumUtil.getByCode(ProductCategoryLevelEnum.values(), entity.getLevel()).getMsg());
        return result;
    }

    public static List<ProductCategoryResponse> generateFromEntityList(List<ProductCategoryEntity> queryProductCategory) {
        List<ProductCategoryResponse> result = BeanUtil.copyListIgnoreNullValue(queryProductCategory, ProductCategoryResponse.class);
        result.forEach(response -> response.setLevelStr(EnumUtil.getByCode(ProductCategoryLevelEnum.values(), response.getLevel()).getMsg()));
        return result;
    }

    public static IPage<ProductCategoryResponse> generateFromEntityPage(IPage<ProductCategoryEntity> queryProductCategory) {
        IPage<ProductCategoryResponse> result = BeanUtil.copyPageEntity(queryProductCategory, ProductCategoryResponse.class);
        result.getRecords().forEach(response -> response.setLevelStr(EnumUtil.getByCode(ProductCategoryLevelEnum.values(), response.getLevel()).getMsg()));
        return result;
    }
}

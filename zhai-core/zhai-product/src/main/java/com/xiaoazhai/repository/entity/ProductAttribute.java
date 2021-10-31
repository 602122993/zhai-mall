package com.xiaoazhai.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhai
 * @since 2021-10-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("zhai_product_attribute")
public class ProductAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 属性录入方式：0->手工录入；1->从列表中选取
     */
    @TableField("input_type")
    private Integer inputType;

    /**
     * 可选值列表，以逗号隔开
     */
    @TableField("input_list")
    private String inputList;

    /**
     * 排序字段：最高的可以单独上传图片
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 是否支持手动新增；0->不支持；1->支持
     */
    @TableField("hand_add_status")
    private Integer handAddStatus;

    /**
     * 属性的类型；0->规格；1->参数
     */
    @TableField("type")
    private Integer type;


}

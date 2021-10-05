package com.xiaoazhai.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

import com.xiaoazhai.domain.entity.PermissionCategoryEntity;
import com.xiaoazhai.entity.BaseDO;
import com.xiaoazhai.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author zhai
 * @since 2021-10-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("zhai_permission_category")
public class PermissionCategory implements Serializable, BaseDO<PermissionCategoryEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;


    @Override
    public PermissionCategoryEntity generateEntity() {
        return generateEntity(PermissionCategoryEntity.class);
    }
}

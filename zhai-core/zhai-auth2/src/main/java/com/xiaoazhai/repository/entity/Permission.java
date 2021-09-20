package com.xiaoazhai.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

import com.xiaoazhai.domain.entity.PermissionEntity;
import com.xiaoazhai.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author zhai
 * @since 2021-09-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("zhai_permission")
public class Permission implements Serializable, BaseDO<PermissionEntity> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("uri")
    private String uri;

    @TableField("description")
    private String description;

    @TableField("created_time")
    private Date createdTime;

    @TableField("updated_time")
    private Date updatedTime;


    @Override
    public PermissionEntity generateEntity() {
        return  generateEntity(PermissionEntity.class);
    }
}

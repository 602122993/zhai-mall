package com.xiaoazhai.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

import com.xiaoazhai.domain.entity.RoleEntity;
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
@TableName("zhai_role")
public class Role implements Serializable, BaseDO<RoleEntity> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    @TableField("name")
    private String name;

    @TableField("code")
    private String code;

    @TableField("description")
    private String description;

    @TableField("status")
    private Integer status;

    @TableField("created_time")
    private Date createdTime;

    @TableField("updated_time")
    private Date updatedTime;


    @Override
    public RoleEntity  generateEntity() {
        return  generateEntity(RoleEntity.class);
    }
}

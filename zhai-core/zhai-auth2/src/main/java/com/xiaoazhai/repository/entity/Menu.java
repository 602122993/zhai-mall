package com.xiaoazhai.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

import com.xiaoazhai.domain.entity.MenuEntity;
import com.xiaoazhai.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author zhai
 * @since 2021-09-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("zhai_menu")
public class Menu implements Serializable, BaseDO<MenuEntity> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单名称
     */
    @TableField("name")
    private String name;

    /**
     * 前端页面
     */
    @TableField("uri")
    private String uri;

    /**
     * 父级id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * icon 图标
     */
    @TableField("icon")
    private String icon;

    @TableField("status")
    private Integer status;

    @TableField("sort")
    private Integer sort;

    @TableField("created_time")
    private Date createdTime;

    @TableField("updated_time")
    private Date updatedTime;


    @Override
    public MenuEntity generateEntity() {
        return generateEntity(MenuEntity.class);
    }
}

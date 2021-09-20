package com.xiaoazhai.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

import com.xiaoazhai.domain.entity.AdminEntity;
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
@TableName("zhai_admin")
public class Admin implements Serializable, BaseDO<AdminEntity> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     */
    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("name")
    private String name;

    @TableField("remark")
    private String remark;

    @TableField("status")
    private Integer status;

    @TableField("last_login_time")
    private Date lastLoginTime;


    @Override
    public AdminEntity generateEntity() {
        return generateEntity(AdminEntity.class);
    }
}

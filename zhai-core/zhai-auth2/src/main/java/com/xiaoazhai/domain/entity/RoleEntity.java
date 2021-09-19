package com.xiaoazhai.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xiaoazhai.entity.BaseEntity;
import com.xiaoazhai.repository.entity.Role;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author jiangyun
 * @date 2021/9/19  14:27
 **/
@Data
public class RoleEntity implements BaseEntity<Role> {
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    private String description;

    private Integer status;

    private Date createdTime;

    private Date updatedTime;

    @Override
    public Role generalDO() {
        return generalDO(Role.class);
    }
}

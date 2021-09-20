package com.xiaoazhai.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xiaoazhai.entity.BaseEntity;
import com.xiaoazhai.repository.entity.RoleMenu;
import lombok.Data;

import java.util.Date;

/**
 * @author jiangyun
 * @date 2021/9/20  14:09
 **/
@Data
public class RoleMenuEntity implements BaseEntity<RoleMenu> {

    private Long id;

    private Long roleId;
    private Long menuId;

    private Date createdTime;

    private Date updatedTime;


    @Override
    public RoleMenu generateDO() {
        return generateDO(RoleMenu.class);
    }
}

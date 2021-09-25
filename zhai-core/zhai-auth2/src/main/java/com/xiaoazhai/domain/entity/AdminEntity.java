package com.xiaoazhai.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xiaoazhai.entity.BaseEntity;
import com.xiaoazhai.repository.entity.Admin;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author jiangyun
 * @date 2021/9/20  15:52
 **/
@Data
public class AdminEntity implements BaseEntity<Admin> {


    private Long id;

    /**
     * 账号
     */
    private String username;

    private String password;

    private String name;

    private String remark;

    private Integer status;

    private Date lastLoginTime;

    private List<MenuEntity> menuList;

    private List<RoleEntity> roleEntityList;


    @Override
    public Admin generateDO() {
        return generateDO(Admin.class);
    }
}

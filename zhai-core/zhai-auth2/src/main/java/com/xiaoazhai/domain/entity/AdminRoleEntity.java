package com.xiaoazhai.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xiaoazhai.entity.BaseEntity;
import com.xiaoazhai.repository.entity.AdminRole;
import lombok.Data;

/**
 * @author jiangyun
 * @date 2021/9/20  15:52
 **/
@Data
public class AdminRoleEntity implements BaseEntity<AdminRole> {

    private Long id;

    private Long adminId;

    private Long roleId;


    @Override
    public AdminRole generateDO() {
        return generateDO(AdminRole.class);
    }
}

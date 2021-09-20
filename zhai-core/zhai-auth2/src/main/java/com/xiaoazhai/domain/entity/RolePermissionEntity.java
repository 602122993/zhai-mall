package com.xiaoazhai.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xiaoazhai.entity.BaseEntity;
import com.xiaoazhai.repository.entity.RolePermission;
import lombok.Data;

/**
 * @author jiangyun
 * @date 2021/9/20  14:16
 **/
@Data
public class RolePermissionEntity implements BaseEntity<RolePermission> {
    private Long id;

    private Long roleId;

    private Long permissionId;

    @Override
    public RolePermission generateDO() {
        return generateDO(RolePermission.class);
    }
}

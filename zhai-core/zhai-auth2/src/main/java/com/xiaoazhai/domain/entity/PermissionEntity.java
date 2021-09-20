package com.xiaoazhai.domain.entity;

import com.xiaoazhai.entity.BaseEntity;
import com.xiaoazhai.repository.entity.Permission;
import lombok.Data;

import java.util.Date;

/**
 * @author jiangyun
 * @date 2021/9/20  12:35
 **/
@Data
public class PermissionEntity implements BaseEntity<Permission> {
    private Long id;

    private String name;

    private String uri;

    private String description;

    private Date createdTime;

    private Date updatedTime;

    @Override
    public Permission generateDO() {
        return  generateDO(Permission.class);
    }
}

package com.xiaoazhai.domain.entity;

import com.xiaoazhai.entity.BaseDO;
import com.xiaoazhai.entity.BaseEntity;
import com.xiaoazhai.repository.entity.PermissionCategory;
import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author jiangyun
 * @date 2021/10/4  16:33
 **/
@Data
public class PermissionCategoryEntity implements BaseEntity<PermissionCategory> {

    private Long id;

    private String name;

    private List<PermissionEntity> permissionEntityList;

    private String createdTime;

    public void formatPermissionList(List<PermissionEntity> list) {
        permissionEntityList = list.stream()
                .filter(permissionEntity -> Objects.equals(permissionEntity.getCategoryId(), id))
                .collect(Collectors.toList());
    }


    @Override
    public PermissionCategory generateDO() {
        return generateDO(PermissionCategory.class);
    }
}

package com.xiaoazhai.auth;

import com.xiaoazhai.domain.entity.AdminEntity;
import com.xiaoazhai.domain.entity.RoleEntity;
import com.xiaoazhai.util.BeanUtil;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jiangyun
 * @date 2021/9/14  16:26
 **/
@Data
public class UserDTO {
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

    private List<RoleEntity> roleEntityList;

    private List<Long> roleIdList;

    public static UserDTO convertFromEntity(AdminEntity adminEntity) {
        UserDTO result = BeanUtil.copyPropertiesIgnoreNullValue(adminEntity, UserDTO.class);
        result.setRoleIdList(adminEntity.getRoleEntityList().stream()
                .map(RoleEntity::getId)
                .collect(Collectors.toList()));
        return result;
    }

}

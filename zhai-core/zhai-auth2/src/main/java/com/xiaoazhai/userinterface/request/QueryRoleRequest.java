package com.xiaoazhai.userinterface.request;

import com.xiaoazhai.entity.BaseQueryForm;
import com.xiaoazhai.repository.entity.Role;
import lombok.Data;

/**
 * @author jiangyun
 * @date 2021/9/19  21:14
 **/
@Data
public class QueryRoleRequest extends BaseQueryForm<Role> {

    private String roleName;
}

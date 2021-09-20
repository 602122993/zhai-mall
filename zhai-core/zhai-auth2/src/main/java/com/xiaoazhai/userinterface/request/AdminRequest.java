package com.xiaoazhai.userinterface.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xiaoazhai.domain.entity.AdminEntity;
import com.xiaoazhai.util.BeanUtil;
import lombok.Data;

/**
 * @author jiangyun
 * @date 2021/9/20  20:44
 **/
@Data
public class AdminRequest {

    private Long id;

    /**
     * 账号
     */
    private String username;

    private String password;

    private String name;

    private String remark;

    private Integer status;

    public AdminEntity generateEntity() {
        return BeanUtil.copyPropertiesIgnoreNullValue(this, AdminEntity.class);
    }
}

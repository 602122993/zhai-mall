package com.xiaoazhai.auth;

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

    private List<Long> roleIdList;


}

package com.xiaoazhai.userinterface.request;

import lombok.Data;

/**
 * @author jiangyun
 * @date 2021/10/8  21:57
 **/
@Data
public class ChangePasswordRequest {

    private Long adminId;

    private String password;
}

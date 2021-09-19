package com.xiaoazhai.exception;

/**
 * @author zhai
 * @date 2020年7月4日13:24:28
 * 全局异常枚举
 */
public enum ErrorCodeEnum {

    PARAM_ERROR("0101001", "参数异常"), USER_UNAUTHORIZED_ERROR("0101002", "用户权限不足"),
    USER_LOGGED_OUT_ERROR("0101003", "当前会话超时请重新登陆"),ERROR_USERNAME_OR_PASSWORD("0101004","帐号或密码错误");

    private String code;

    private String msg;

    ErrorCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }




    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}

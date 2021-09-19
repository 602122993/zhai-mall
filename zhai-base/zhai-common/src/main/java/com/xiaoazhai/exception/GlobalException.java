package com.xiaoazhai.exception;

import lombok.Data;

/**
 * @author zhai
 * @date 2020年7月4日13:22:17
 * 全局异常
 */

@Data
public class GlobalException extends RuntimeException {

    private ErrorCodeEnum errorCodeEnum;

    public GlobalException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMsg());
        this.errorCodeEnum = errorCodeEnum;
        this.code= errorCodeEnum.getCode();
        this.message=errorCodeEnum.getMsg();
    }

    public GlobalException(String message){
        super(message);
        this.message=message;
        this.code="-1";
    }


    private String code;


    private String message;


    public ErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }

    public void setErrorCodeEnum(ErrorCodeEnum errorCodeEnum) {
        this.errorCodeEnum = errorCodeEnum;
    }
}

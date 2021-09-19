package com.xiaoazhai.result;

import com.xiaoazhai.exception.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhai
 * @date 2020年7月4日16:19:35
 * 返回消息体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReturnMessage {


    private int status=200;

    private String code;

    private String message;

    private Object data;



    public static ReturnMessage success() {
        return ReturnMessage.builder().code("200").message("").build();
    }

    public static ReturnMessage success(Object o){
        return ReturnMessage.builder().code("200").data(o).build();
    }



    public static ReturnMessage fail(ErrorCodeEnum codeEnum) {
        return ReturnMessage.builder().code(codeEnum.getCode()).message(codeEnum.getMsg()).build();
    }





}

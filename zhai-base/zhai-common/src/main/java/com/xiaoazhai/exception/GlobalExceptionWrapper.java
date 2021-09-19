package com.xiaoazhai.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhai
 * @date 2020/9/21  17:52
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GlobalExceptionWrapper extends RuntimeException implements Serializable {


    private GlobalException globalException;


}

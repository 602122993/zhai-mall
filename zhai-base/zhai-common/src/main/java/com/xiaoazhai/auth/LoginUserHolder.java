package com.xiaoazhai.auth;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jiangyun
 * @date 2021/9/21  14:09
 **/
@Component
@Slf4j
public class LoginUserHolder {
    //
    public UserDTO getCurrentUser() {
        //从Header中获取用户信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userStr = Base64.decodeStr(request.getHeader("user"));
        JSONObject userJsonObject = JSONObject.parseObject(userStr);
        UserDTO userDTO = new UserDTO();
        log.info("用户信息为-----------{}", userStr);
        userDTO.setUsername(userJsonObject.getString("user_name"));
        userDTO.setId(Convert.toLong(userJsonObject.get("id")));
        userDTO.setRoleIdList(Convert.toList(Long.class,userJsonObject.get("authorities")));
        return userDTO;
    }
}
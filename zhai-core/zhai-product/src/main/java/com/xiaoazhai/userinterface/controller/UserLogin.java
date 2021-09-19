package com.xiaoazhai.userinterface.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author jiangyun
 * @date 2021/9/12  20:25
 **/
@RestController
@RequestMapping("user")
public class UserLogin {

    @RequestMapping("login")
    public LoginResult login() {
        return new LoginResult();
    }


    @RequestMapping("info")
    public UserInfo userInfo() {
        return new UserInfo();
    }

    @Data
    public static class UserInfo {
        private List<String> roles = Arrays.asList("admin");
        private String introduction = "I am a super administrator";
        private String avatar = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
        private String name = "azhai";
    }

    @Data
    public static class LoginResult {
        private String token = "admin-token";
    }
}

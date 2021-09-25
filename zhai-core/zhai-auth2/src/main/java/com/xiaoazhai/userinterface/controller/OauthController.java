package com.xiaoazhai.userinterface.controller;

import com.xiaoazhai.result.ReturnMessage;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author jiangyun
 * @date 2021/9/21  16:21
 **/
@RestController
@RequestMapping("oauth")
public class OauthController {

    @Resource
    private TokenEndpoint tokenEndpoint;

    @PostMapping("/token")
    public ReturnMessage postAccessToken(Principal principal, @RequestParam Map parameters) throws HttpRequestMethodNotSupportedException {
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Map result = new LinkedHashMap(token.getAdditionalInformation());
        result.put("accessToken", token.getValue());
        if (token.getRefreshToken() != null) {
            result.put("refreshToken", token.getRefreshToken().getValue());
        }
        return ReturnMessage.success(result);

    }
}

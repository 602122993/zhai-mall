package com.xiaoazhai.auth;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSON;
import com.xiaoazhai.result.ReturnMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author jiangyun
 * @date 2021/9/23  21:37
 **/
public class AdminLogoutHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf8");
        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.write(JSON.toJSONString(ReturnMessage.success()));
        printWriter.flush();
        printWriter.close();
    }
}

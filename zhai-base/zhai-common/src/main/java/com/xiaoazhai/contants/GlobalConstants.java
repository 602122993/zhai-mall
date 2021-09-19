package com.xiaoazhai.contants;

/**
 * @author zhai
 * @date 2020年7月4日09:33:18
 * 全局变量
 */
public class GlobalConstants {

    /**
     * 用户登录认证信息中所有权限前缀
     */
    public static final String ALL_AUTHORITIES_PREFIX = "#ALL_AUTH#";

    /**
     * AccessToken超时时间，15天
     */
    public static final int ACCESS_TOKEN_TIMEOUT = 60 * 60 * 24 * 15;
    /**
     * AccessToken刷新用token超时时间，7天
     */
    public static final int REFRESH_TOKEN_TIMEOUT = 60 * 60 * 24 * 7;

}

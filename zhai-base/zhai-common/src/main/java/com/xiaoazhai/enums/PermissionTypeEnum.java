package com.xiaoazhai.enums;

/**
 * @author zhai
 * 权限类型枚举
 */
public enum PermissionTypeEnum {

    MENU(1,"菜单"),BUTTON(2,"按钮");


    private int code;

    private String msg;

    PermissionTypeEnum(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public int getCode(){return code;}



}

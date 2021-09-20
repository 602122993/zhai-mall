package com.xiaoazhai.userinterface.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xiaoazhai.domain.entity.MenuEntity;
import com.xiaoazhai.util.BeanUtil;
import lombok.Data;

/**
 * @author jiangyun
 * @date 2021/9/20  13:40
 **/
@Data
public class MenuRequest {

    private Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 前端页面
     */
    private String uri;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * icon 图标
     */
    private String icon;

    private Integer status;

    private Integer sort;

    public MenuEntity generateEntity() {
        return BeanUtil.copyPropertiesIgnoreNullValue(this, MenuEntity.class);
    }
}

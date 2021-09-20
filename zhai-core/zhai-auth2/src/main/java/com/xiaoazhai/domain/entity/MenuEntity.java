package com.xiaoazhai.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xiaoazhai.entity.BaseEntity;
import com.xiaoazhai.repository.entity.Menu;
import lombok.Data;

import java.util.Date;

/**
 * @author jiangyun
 * @date 2021/9/20  13:30
 **/
@Data
public class MenuEntity implements BaseEntity<Menu> {
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


    @Override
    public Menu generateDO() {
        return generateDO(Menu.class);
    }
}

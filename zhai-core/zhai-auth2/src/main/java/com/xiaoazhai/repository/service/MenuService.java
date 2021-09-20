package com.xiaoazhai.repository.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoazhai.domain.entity.MenuEntity;
import com.xiaoazhai.repository.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhai
 * @since 2021-09-20
 */
public interface MenuService extends IService<Menu> {

    IPage<MenuEntity> queryMenuPage(Page page, String name);

    void saveMenu(Menu generateDO);

    Menu queryMenuById(Long id);

    void updateMenuById(Menu generateDO);

    void removeMenuById(Long id);
}

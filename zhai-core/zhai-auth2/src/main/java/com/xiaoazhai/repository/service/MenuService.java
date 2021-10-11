package com.xiaoazhai.repository.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoazhai.domain.entity.MenuEntity;
import com.xiaoazhai.repository.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhai
 * @since 2021-09-20
 */
public interface MenuService extends IService<Menu> {

    IPage<MenuEntity> queryMenuPage(Page page, Long parentId, String name);

    void saveMenu(Menu generateDO);

    Menu queryMenuById(Long id);

    void updateMenuById(Menu generateDO);

    void removeMenuById(Long id);

    List<MenuEntity> queryMenuListByIds(List<Long> ids);

    List<MenuEntity> queryMenuListByParentId(Long baseParentMenuId);

}

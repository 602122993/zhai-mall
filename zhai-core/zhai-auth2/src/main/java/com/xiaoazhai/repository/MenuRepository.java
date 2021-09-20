package com.xiaoazhai.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoazhai.domain.entity.MenuEntity;
import com.xiaoazhai.repository.service.MenuService;
import com.xiaoazhai.util.BeanUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author jiangyun
 * @date 2021/9/20  13:26
 **/
@Component
public class MenuRepository {

    @Resource
    private MenuService menuService;

    public IPage<MenuEntity> queryMenuPage(Page page, String name) {
        return menuService.queryMenuPage(page, name);
    }

    public void saveMenu(MenuEntity menuEntity) {
        menuService.saveMenu(menuEntity.generateDO());
    }

    public MenuEntity queryMenuById(Long id) {
        return BeanUtil.doToEntity(menuService.queryMenuById(id), MenuEntity.class);
    }

    public void updateMenu(MenuEntity menuEntity) {
        menuService.updateMenuById(menuEntity.generateDO());
    }

    public void removeMenuById(Long id) {
        menuService.removeMenuById(id);
    }
}

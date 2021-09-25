package com.xiaoazhai.repository;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoazhai.domain.entity.MenuEntity;
import com.xiaoazhai.domain.entity.RoleEntity;
import com.xiaoazhai.domain.entity.RoleMenuEntity;
import com.xiaoazhai.repository.entity.RoleMenu;
import com.xiaoazhai.repository.service.MenuService;
import com.xiaoazhai.repository.service.RoleMenuService;
import com.xiaoazhai.util.BeanUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author jiangyun
 * @date 2021/9/20  13:26
 **/
@Component
public class MenuRepository {

    public static final Long BASE_PARENT_MENU_ID = 0L;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private MenuService menuService;

    @Resource
    private RoleMenuService roleMenuService;

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

    public List<MenuEntity> queryTreeMenu() {
        List<MenuEntity> allMenuList = BeanUtil.doToEntityBatch(menuService.list(), MenuEntity.class);
        Map<Long, List<MenuEntity>> menuMapByParentId = allMenuList.stream()
                .collect(Collectors.groupingBy(MenuEntity::getParentId));
        List<MenuEntity> resultList = menuMapByParentId.get(BASE_PARENT_MENU_ID);
        fillChildrenMenu(resultList, menuMapByParentId);

        return resultList;
    }

    private void fillChildrenMenu(List<MenuEntity> list, Map<Long, List<MenuEntity>> allMap) {
        list.forEach(menuEntity -> {
            if (allMap.get(menuEntity.getId()) != null) {
                fillChildrenMenu(allMap.get(menuEntity.getId()), allMap);
            }
            menuEntity.setChildList(allMap.get(menuEntity.getId()));
            menuEntity.setRoleIdList(roleRepository.queryAllRoleListByMenuId(menuEntity.getId())
                    .stream()
                    .map(RoleEntity::getId)
                    .collect(Collectors.toList()));
        });
    }

    public List<MenuEntity> queryByAdminId(Long id) {
        return Optional.ofNullable(id)
                .map(roleRepository::queryByAdminId)
                .map(roleEntities -> roleEntities.stream()
                        .map(RoleEntity::getId)
                        .collect(Collectors.toList()))
                .filter(CollectionUtil::isNotEmpty)
                .map(roleMenuService::queryListByRoleIdList)
                .map(roleMenuEntityList -> roleMenuEntityList.stream()
                        .map(RoleMenuEntity::getMenuId)
                        .collect(Collectors.toList()))
                .filter(CollectionUtil::isNotEmpty)
                .map(menuService::queryMenuListByIds)
                .orElseGet(ArrayList::new);

    }
}

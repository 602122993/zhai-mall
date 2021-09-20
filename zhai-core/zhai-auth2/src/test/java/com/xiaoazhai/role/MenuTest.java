package com.xiaoazhai.role;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiaoazhai.domain.entity.MenuEntity;
import com.xiaoazhai.domain.entity.PermissionEntity;
import com.xiaoazhai.enums.CommonStatusEnum;
import com.xiaoazhai.repository.entity.Menu;
import com.xiaoazhai.repository.entity.Permission;
import com.xiaoazhai.repository.service.MenuService;
import com.xiaoazhai.result.ReturnMessage;
import com.xiaoazhai.userinterface.controller.MenuController;
import com.xiaoazhai.userinterface.request.MenuRequest;
import com.xiaoazhai.userinterface.request.PermissionRequest;
import com.xiaoazhai.userinterface.request.QueryMenuRequest;
import com.xiaoazhai.userinterface.request.QueryPermissionRequest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author jiangyun
 * @date 2021/9/19  21:15
 **/
@SpringBootTest()
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
public class MenuTest {


    private static Long id;
    @Resource
    private MenuController menuController;
    @Resource
    private MenuService menuService;

    @Test
    public void test1_addMenu() {
        MenuRequest menuRequest = new MenuRequest();
        menuRequest.setIcon("icon");
        menuRequest.setName("测试ing成");
        menuRequest.setUri("testuri");
        menuRequest.setSort(0);
        menuRequest.setStatus(CommonStatusEnum.USED.getCode());
        menuController.saveMenu(menuRequest);
        id = this.menuService.getOne(Wrappers.<Menu>lambdaQuery().orderByDesc(Menu::getId).last("limit 1")).getId();
        System.out.println(id + "------------------");
    }

    @Test
    public void test2_menuList() {
        QueryMenuRequest queryMenuRequest = new QueryMenuRequest();
        queryMenuRequest.setName("测试");
        queryMenuRequest.setCurrent(1);
        queryMenuRequest.setLimit(10);
        ReturnMessage result = menuController.queryMenuList(queryMenuRequest);
        List<?> list = ((IPage<?>) result.getData()).getRecords();
        Assert.isTrue(CollectionUtil.isNotEmpty(list));
    }

    @Test
    public void test3_getMenuById() {
        Assert.notNull(menuController.queryMenuById(id).getData());
    }

    @Test
    public void test4_updateMenu() {
        MenuRequest menuRequest = new MenuRequest();
        menuRequest.setId(id);
        menuRequest.setIcon("updateicon");
        menuRequest.setUri("updateuri");
        menuRequest.setName("测试ing成");
        menuRequest.setSort(0);
        menuRequest.setStatus(CommonStatusEnum.UN_USED.getCode());
        menuController.updateMenu(menuRequest);
        MenuEntity menu = (MenuEntity) menuController.queryMenuById(id).getData();
        Assert.isTrue(menu != null
                && Objects.equals(menu.getId(), menuRequest.getId())
                && Objects.equals(menu.getIcon(), menuRequest.getIcon())
                && Objects.equals(menu.getUri(), menuRequest.getUri())
                && Objects.equals(menu.getName(), menuRequest.getName())
                && Objects.equals(menu.getStatus(), menuRequest.getStatus())
                && Objects.equals(menu.getSort(), menuRequest.getSort())
        );
    }

    @Test
    public void test5_removeMenu() {
        menuController.removeMenuById(id);
        Assert.isNull(menuController.queryMenuById(id).getData());
    }


}

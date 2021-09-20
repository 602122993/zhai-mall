package com.xiaoazhai.role;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiaoazhai.domain.entity.AdminEntity;
import com.xiaoazhai.domain.entity.MenuEntity;
import com.xiaoazhai.enums.CommonStatusEnum;
import com.xiaoazhai.repository.entity.Admin;
import com.xiaoazhai.repository.entity.Menu;
import com.xiaoazhai.repository.service.AdminService;
import com.xiaoazhai.result.ReturnMessage;
import com.xiaoazhai.userinterface.controller.AdminController;
import com.xiaoazhai.userinterface.request.AdminRequest;
import com.xiaoazhai.userinterface.request.MenuRequest;
import com.xiaoazhai.userinterface.request.QueryAdminRequest;
import com.xiaoazhai.userinterface.request.QueryMenuRequest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class AdminTest {


    private static Long id;
    @Resource
    private AdminController adminController;
    @Resource
    private AdminService adminService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    public void test1_addMenu() {
        AdminRequest adminRequest = new AdminRequest();
        adminRequest.setPassword("123456");
        adminRequest.setUsername("username");
        adminRequest.setRemark("测试备注");
        adminRequest.setStatus(CommonStatusEnum.USED.getCode());
        adminController.saveAdmin(adminRequest);
        id = this.adminService.getOne(Wrappers.<Admin>lambdaQuery().orderByDesc(Admin::getId).last("limit 1")).getId();
        System.out.println(id + "------------------");
    }

    @Test
    public void test2_menuList() {
        QueryAdminRequest queryAdminRequest = new QueryAdminRequest();
        queryAdminRequest.setName("测试");
        queryAdminRequest.setCurrent(1);
        queryAdminRequest.setLimit(10);
        ReturnMessage result = adminController.queryAdminList(queryAdminRequest);
        List<?> list = ((IPage<?>) result.getData()).getRecords();
        Assert.isTrue(CollectionUtil.isNotEmpty(list));
    }

    @Test
    public void test3_getMenuById() {
        Assert.notNull(adminController.queryAdminById(id).getData());
    }

    @Test
    public void test4_updateMenu() {
        AdminRequest adminRequest = new AdminRequest();
        adminRequest.setPassword("123456");
        adminRequest.setUsername("username");
        adminRequest.setRemark("测试备注");
        adminRequest.setStatus(CommonStatusEnum.USED.getCode());
        adminController.updateAdmin(adminRequest);
        AdminEntity admin = (AdminEntity) adminController.queryAdminById(id).getData();
        Assert.isTrue(admin != null
                && Objects.equals(admin.getId(), adminRequest.getId())
                && Objects.equals(admin.getRemark(), adminRequest.getRemark())
                && Objects.equals(admin.getUsername(), adminRequest.getUsername())
                && Objects.equals(admin.getPassword(), passwordEncoder.encode(adminRequest.getPassword()))
                && Objects.equals(admin.getStatus(), adminRequest.getStatus()));
    }

    @Test
    public void test5_removeMenu() {
        adminController.deleteAdminById(id);
        Assert.isNull(adminController.queryAdminById(id).getData());
    }


}

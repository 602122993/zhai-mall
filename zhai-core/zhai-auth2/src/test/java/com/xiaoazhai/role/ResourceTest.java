package com.xiaoazhai.role;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiaoazhai.domain.entity.PermissionEntity;
import com.xiaoazhai.domain.entity.RoleEntity;
import com.xiaoazhai.enums.CommonStatusEnum;
import com.xiaoazhai.repository.entity.Permission;
import com.xiaoazhai.repository.service.PermissionService;
import com.xiaoazhai.result.ReturnMessage;
import com.xiaoazhai.userinterface.controller.PermissionController;
import com.xiaoazhai.userinterface.request.QueryPermissionRequest;
import com.xiaoazhai.userinterface.request.QueryRoleRequest;
import com.xiaoazhai.userinterface.request.PermissionRequest;
import com.xiaoazhai.userinterface.request.RoleRequest;
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
public class ResourceTest {


    private static Long id;
    @Resource
    private PermissionController resourceController;
    @Resource
    private PermissionService resourceService;

    @Test
    public void test1_addPermission() {
        PermissionRequest addRoleRequest = new PermissionRequest();
        addRoleRequest.setDescription("测试描述");
        addRoleRequest.setName("测试昵称");
        resourceController.savePermission(addRoleRequest);
        id = this.resourceService.getOne(Wrappers.<Permission>lambdaQuery().orderByDesc(Permission::getId).last("limit 1")).getId();
        System.out.println(id + "------------------");
    }

    @Test
    public void test2_permissionList() {
        QueryPermissionRequest queryPermissionRequest = new QueryPermissionRequest();
        queryPermissionRequest.setName("测试");
        queryPermissionRequest.setCurrent(1);
        queryPermissionRequest.setLimit(10);
        ReturnMessage result = resourceController.queryPermissionList(queryPermissionRequest);
        List<?> list = ((IPage<?>) result.getData()).getRecords();
        Assert.isTrue(CollectionUtil.isNotEmpty(list));
    }

    @Test
    public void test3_getPermissionById() {
        Assert.notNull(resourceController.queryPermissionById(id).getData());
    }

    @Test
    public void test4_updateRole() {
        PermissionRequest permissionRequest = new PermissionRequest();
        permissionRequest.setId(id);
        permissionRequest.setDescription("测试修改描述");
        permissionRequest.setName("测试修改昵称");
        permissionRequest.setUri("testuri");
        resourceController.updatePermission(permissionRequest);
        PermissionEntity role = (PermissionEntity) resourceController.queryPermissionById(id).getData();
        Assert.isTrue(role != null
                && Objects.equals(role.getId(), permissionRequest.getId())
                && Objects.equals(role.getDescription(), permissionRequest.getDescription())
                && Objects.equals(role.getUri(), permissionRequest.getUri())
                && Objects.equals(role.getName(), permissionRequest.getName())
        );
    }

    @Test
    public void test5_removeRole() {
        resourceController.deleteById(id);
        Assert.isNull(resourceController.queryPermissionById(id).getData());
    }


}

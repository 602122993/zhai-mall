package com.xiaoazhai.role;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiaoazhai.domain.entity.RoleEntity;
import com.xiaoazhai.enums.CommonStatusEnum;
import com.xiaoazhai.repository.entity.Role;
import com.xiaoazhai.repository.service.RoleService;
import com.xiaoazhai.result.ReturnMessage;
import com.xiaoazhai.userinterface.controller.RoleController;
import com.xiaoazhai.userinterface.request.RoleRequest;
import com.xiaoazhai.userinterface.request.QueryRoleRequest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author jiangyun
 * @date 2021/9/19  21:15
 **/
@SpringBootTest()
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
public class RoleTest {


    private static Long roleId;
    @Resource
    private RoleController roleController;
    @Resource
    private RoleService roleService;

    @Test
    public void test1_addRole() {
        RoleRequest addRoleRequest = new RoleRequest();
        addRoleRequest.setDescription("测试描述");
        addRoleRequest.setName("测试昵称");
        addRoleRequest.setStatus(CommonStatusEnum.USED.getCode());
        roleController.saveRole(addRoleRequest);
        roleId = this.roleService.getOne(Wrappers.<Role>lambdaQuery().orderByDesc(Role::getId).last("limit 1")).getId();
        System.out.println(roleId + "------------------");
    }

    @Test
    public void test2_roleList() {
        QueryRoleRequest queryRoleRequest = new QueryRoleRequest();
        queryRoleRequest.setRoleName("测试");
        queryRoleRequest.setCurrent(1);
        queryRoleRequest.setLimit(10);
        ReturnMessage result = roleController.queryRoleList(queryRoleRequest);
        List<?> list = ((IPage<?>) result.getData()).getRecords();
        Assert.isTrue(CollectionUtil.isNotEmpty(list));
    }

    @Test
    public void test3_getRoleById() {
        Assert.notNull(roleController.queryRoleById(roleId).getData());
    }

    @Test
    public void test4_updateRole() {
        RoleRequest addRoleRequest = new RoleRequest();
        addRoleRequest.setId(roleId);
        addRoleRequest.setDescription("测试修改描述");
        addRoleRequest.setName("测试修改昵称");
        addRoleRequest.setStatus(CommonStatusEnum.UN_USED.getCode());
        roleController.updateRole(addRoleRequest);
        RoleEntity role = (RoleEntity) roleController.queryRoleById(roleId).getData();
        Assert.isTrue(role != null
                && Objects.equals(role.getId(), addRoleRequest.getId())
                && Objects.equals(role.getDescription(), addRoleRequest.getDescription())
                && Objects.equals(role.getStatus(), addRoleRequest.getStatus())
                && Objects.equals(role.getName(), addRoleRequest.getName())
        );
    }

    @Test
    public void test5_removeRole() {
        roleController.deleteRole(roleId);
        Assert.isNull(roleController.queryRoleById(roleId).getData());
    }


}

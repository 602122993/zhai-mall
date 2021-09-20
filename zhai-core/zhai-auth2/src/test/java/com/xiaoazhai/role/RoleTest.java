package com.xiaoazhai.role;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiaoazhai.domain.entity.RoleEntity;
import com.xiaoazhai.enums.CommonStatusEnum;
import com.xiaoazhai.repository.entity.Role;
import com.xiaoazhai.repository.entity.RoleMenu;
import com.xiaoazhai.repository.entity.RolePermission;
import com.xiaoazhai.repository.service.RoleMenuService;
import com.xiaoazhai.repository.service.RolePermissionService;
import com.xiaoazhai.repository.service.RoleService;
import com.xiaoazhai.result.ReturnMessage;
import com.xiaoazhai.userinterface.controller.RoleController;
import com.xiaoazhai.userinterface.request.DistributionMenuRequest;
import com.xiaoazhai.userinterface.request.DistributionPermissionRequest;
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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Resource
    private RoleMenuService roleMenuService;
    @Resource
    private RolePermissionService rolePermissionService;

    @Test
    public void testa_addRole() {
        RoleRequest addRoleRequest = new RoleRequest();
        addRoleRequest.setDescription("测试描述");
        addRoleRequest.setName("测试昵称");
        addRoleRequest.setStatus(CommonStatusEnum.USED.getCode());
        roleController.saveRole(addRoleRequest);
        roleId = this.roleService.getOne(Wrappers.<Role>lambdaQuery().orderByDesc(Role::getId).last("limit 1")).getId();
        System.out.println(roleId + "------------------");
    }

    @Test
    public void testb_roleList() {
        QueryRoleRequest queryRoleRequest = new QueryRoleRequest();
        queryRoleRequest.setRoleName("测试");
        queryRoleRequest.setCurrent(1);
        queryRoleRequest.setLimit(10);
        ReturnMessage result = roleController.queryRoleList(queryRoleRequest);
        List<?> list = ((IPage<?>) result.getData()).getRecords();
        Assert.isTrue(CollectionUtil.isNotEmpty(list));
    }

    @Test
    public void testc_getRoleById() {
        Assert.notNull(roleController.queryRoleById(roleId).getData());
    }

    @Test
    public void testd_updateRole() {
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
    public void teste_addRoleMenu() {
        DistributionMenuRequest request = new DistributionMenuRequest();
        request.setRoleId(roleId);
        request.setMenuIdList(Arrays.asList(1L, 2L, 3L));
        roleController.distributionMenu(request);
        List<Long> result = roleMenuService.list(Wrappers.<RoleMenu>lambdaQuery()
                .eq(RoleMenu::getRoleId, roleId))
                .stream()
                .collect(Collectors.groupingBy(RoleMenu::getRoleId))
                .get(roleId)
                .stream()
                .map(RoleMenu::getMenuId)
                .sorted()
                .collect(Collectors.toList());
        Assert.isTrue(result.equals(request.getMenuIdList()));
    }

    @Test
    public void testf_addRolePermission() {
        DistributionPermissionRequest request = new DistributionPermissionRequest();
        request.setRoleId(roleId);
        request.setPermissionIdList(Arrays.asList(3L, 4L, 5L));
        roleController.distributionPermission(request);
        List<Long> result = rolePermissionService.list(Wrappers.<RolePermission>lambdaQuery()
                .eq(RolePermission::getRoleId, roleId))
                .stream()
                .collect(Collectors.groupingBy(RolePermission::getRoleId))
                .get(roleId)
                .stream()
                .map(RolePermission::getPermissionId)
                .sorted()
                .collect(Collectors.toList());
        Assert.isTrue(result.equals(request.getPermissionIdList()));
    }


    @Test
    public void testz_removeRole() {
        roleController.deleteRole(roleId);
        Assert.isNull(roleController.queryRoleById(roleId).getData());
    }

}

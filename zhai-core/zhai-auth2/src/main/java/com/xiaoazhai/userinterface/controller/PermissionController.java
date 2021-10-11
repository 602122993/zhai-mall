package com.xiaoazhai.userinterface.controller;

import com.xiaoazhai.repository.PermissionRepository;
import com.xiaoazhai.result.ReturnMessage;
import com.xiaoazhai.userinterface.request.PermissionCategoryRequest;
import com.xiaoazhai.userinterface.request.QueryPermissionRequest;
import com.xiaoazhai.userinterface.request.PermissionRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author jiangyun
 * @date 2021/9/20  12:17
 **/
@RestController
@RequestMapping("permission")
public class PermissionController {

    @Resource
    private PermissionRepository permissionRepository;

    @GetMapping("list")
    public ReturnMessage queryPermissionList(QueryPermissionRequest queryPermissionRequest) {
        return ReturnMessage.success(permissionRepository.queryPermissionPage(queryPermissionRequest.getPage(), queryPermissionRequest.getName()));
    }

    @PostMapping("save")
    public ReturnMessage savePermission(@RequestBody PermissionRequest permissionRequest) {
        permissionRepository.savePermission(permissionRequest.generateEntity());
        return ReturnMessage.success();
    }


    @PostMapping("update")
    public ReturnMessage updatePermission(@RequestBody PermissionRequest permissionRequest) {
        permissionRepository.updatePermissionById(permissionRequest.generateEntity());
        return ReturnMessage.success();
    }

    @GetMapping("permission/{id}")
    public ReturnMessage queryPermissionById(@PathVariable("id") Long id) {
        return ReturnMessage.success(permissionRepository.queryPermissionById(id));
    }

    @PostMapping("remove")
    public ReturnMessage deleteById(Long id) {
        permissionRepository.deleteById(id);
        return ReturnMessage.success();
    }

    @GetMapping("query-by-role-id")
    public ReturnMessage queryByRoleId(Long roleId) {
        return ReturnMessage.success(permissionRepository.queryPermissionByRoleId(roleId));
    }

    @GetMapping("query-all-permission-tree")
    public ReturnMessage queryAllPermissionTree() {
        return ReturnMessage.success(permissionRepository.queryPermissionTreeList());
    }

    @GetMapping("permission-category/list")
    public ReturnMessage permissionCategoryList() {
        return ReturnMessage.success(permissionRepository.queryPermissionCategoryList());
    }


    @PostMapping("permission-category/create")
    public ReturnMessage createPermissionCategory(@RequestBody PermissionCategoryRequest permissionCategoryRequest) {
        permissionRepository.savePermissionCategory(permissionCategoryRequest.generalEntity());
        return ReturnMessage.success();
    }


    @PostMapping("permission-category/update")
    public ReturnMessage updatePermissionCategory(@RequestBody PermissionCategoryRequest permissionCategoryRequest) {
        permissionRepository.updatePermissionCategoryById(permissionCategoryRequest.generalEntity());
        return ReturnMessage.success();
    }


    @PostMapping("permission-category/remove")
    public ReturnMessage removePermissionCategory(Long id) {
        permissionRepository.removePermissionCategoryById(id);
        return ReturnMessage.success();
    }


}

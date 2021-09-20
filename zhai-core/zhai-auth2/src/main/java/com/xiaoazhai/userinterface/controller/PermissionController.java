package com.xiaoazhai.userinterface.controller;

import com.xiaoazhai.repository.PermissionRepository;
import com.xiaoazhai.result.ReturnMessage;
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
    public ReturnMessage savePermission(PermissionRequest permissionRequest) {
        permissionRepository.savePermission(permissionRequest.generateEntity());
        return ReturnMessage.success();
    }


    @PostMapping("update")
    public ReturnMessage updatePermission(PermissionRequest permissionRequest) {
        permissionRepository.updatePermissionById(permissionRequest.generateEntity());
        return ReturnMessage.success();
    }

    @GetMapping("permission/{id}")
    public ReturnMessage queryPermissionById(@PathVariable("id") Long id) {
        return ReturnMessage.success(permissionRepository.queryPermissionById(id));
    }

    @PostMapping("delete")
    public ReturnMessage deleteById(Long id) {
        permissionRepository.deleteById(id);
        return ReturnMessage.success();
    }

}

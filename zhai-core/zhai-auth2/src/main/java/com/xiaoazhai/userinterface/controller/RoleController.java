package com.xiaoazhai.userinterface.controller;

import com.xiaoazhai.repository.RoleRepository;
import com.xiaoazhai.result.ReturnMessage;
import com.xiaoazhai.userinterface.request.RoleRequest;
import com.xiaoazhai.userinterface.request.QueryRoleRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author jiangyun
 * @date 2021/9/19  14:22
 **/
@RestController
@RequestMapping("role")
public class RoleController {

    @Resource
    private RoleRepository roleRepository;

    @GetMapping("list")
    public ReturnMessage queryRoleList(QueryRoleRequest request) {
        return ReturnMessage.success(roleRepository.queryRolePage(request.getPage(), request.getRoleName()));
    }

    @PostMapping("save")
    public ReturnMessage saveRole(RoleRequest addRoleRequest) {
        roleRepository.saveRole(addRoleRequest.generalEntity());
        return ReturnMessage.success();
    }

    @PostMapping("update")
    public ReturnMessage updateRole(RoleRequest roleRequest) {
        roleRepository.updateRole(roleRequest.generalEntity());
        return ReturnMessage.success();
    }

    @PostMapping("delete")
    public ReturnMessage deleteRole(Long id) {
        roleRepository.deleteRole(id);
        return ReturnMessage.success();
    }

    @GetMapping("role/{id}")
    public ReturnMessage queryRoleById(@PathVariable("id") Long id) {
        return ReturnMessage.success(roleRepository.queryById(id));
    }
}
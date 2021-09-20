package com.xiaoazhai.userinterface.controller;

import com.xiaoazhai.repository.AdminRepository;
import com.xiaoazhai.result.ReturnMessage;
import com.xiaoazhai.userinterface.request.AdminRequest;
import com.xiaoazhai.userinterface.request.DistributionAdminRoleRequest;
import com.xiaoazhai.userinterface.request.QueryAdminRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author jiangyun
 * @date 2021/9/20  20:39
 **/
@RestController
@RequestMapping("admin")
public class AdminController {

    @Resource
    private AdminRepository adminRepository;


    @GetMapping("list")
    public ReturnMessage queryAdminList(QueryAdminRequest request) {
        return ReturnMessage.success(adminRepository.queryAdminList(request.getPage(), request.getName()));
    }

    @PostMapping("save")
    public ReturnMessage saveAdmin(AdminRequest adminRequest) {
        adminRepository.saveAdmin(adminRequest.generateEntity());
        return ReturnMessage.success();
    }

    @PostMapping("update")
    public ReturnMessage updateAdmin(AdminRequest request) {
        adminRepository.updateAdminById(request.generateEntity());
        return ReturnMessage.success();
    }

    @GetMapping("admin/{id}")
    public ReturnMessage queryAdminById(@PathVariable Long id) {
        return ReturnMessage.success(adminRepository.queryAdminById(id));
    }

    @PostMapping("delete")
    public ReturnMessage deleteAdminById(Long id) {
        adminRepository.deleteAdminById(id);
        return ReturnMessage.success();
    }

    @PostMapping("description-role")
    public ReturnMessage distributionAdminRole(DistributionAdminRoleRequest request) {
        adminRepository.distributionAdminRole(request.getAdminId(), request.generateAdminRoleEntity());
        return ReturnMessage.success();
    }

}

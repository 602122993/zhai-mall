package com.xiaoazhai.userinterface.controller;

import com.xiaoazhai.annotation.LoginUser;
import com.xiaoazhai.auth.LoginUserHolder;
import com.xiaoazhai.auth.UserDTO;
import com.xiaoazhai.repository.MenuRepository;
import com.xiaoazhai.result.ReturnMessage;
import com.xiaoazhai.userinterface.request.MenuRequest;
import com.xiaoazhai.userinterface.request.QueryMenuRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author jiangyun
 * @date 2021/9/20  13:25
 **/
@RestController
@RequestMapping("menu")
public class MenuController {

    @Resource
    private MenuRepository menuRepository;


    @GetMapping("list")
    public ReturnMessage queryMenuList(QueryMenuRequest request) {
        return ReturnMessage.success(menuRepository.queryMenuPage(request.getPage(), request.getParentId(), request.getName()));
    }

    @PostMapping("save")
    public ReturnMessage saveMenu(@RequestBody MenuRequest menuRequest) {
        menuRepository.saveMenu(menuRequest.generateEntity());
        return ReturnMessage.success();
    }

    @GetMapping("menu/{id}")
    public ReturnMessage queryMenuById(@PathVariable("id") Long id) {
        return ReturnMessage.success(menuRepository.queryMenuById(id));
    }

    @PostMapping("update")
    public ReturnMessage updateMenu(@RequestBody MenuRequest menuRequest) {
        menuRepository.updateMenu(menuRequest.generateEntity());
        return ReturnMessage.success();
    }

    @PostMapping("remove")
    public ReturnMessage removeMenuById(Long id) {
        menuRepository.removeMenuById(id);
        return ReturnMessage.success();
    }

    @GetMapping("query-all-menu")
    public ReturnMessage queryAllMenu() {
        return ReturnMessage.success(menuRepository.queryTreeMenu());
    }

    @GetMapping("query-by-role-id")
    public ReturnMessage queryByRoleId(String roleId) {
        return ReturnMessage.success(menuRepository.queryMenuIdListByRoleId(roleId));
    }

    @GetMapping("query-root-menu")
    public ReturnMessage queryAllMenuNoPage() {
        return ReturnMessage.success(menuRepository.queryRootMenuList());
    }

    @GetMapping("query-admin-menu-list")
    public ReturnMessage queryAdminMenuList(@LoginUser UserDTO userDTO) {
        return ReturnMessage.success(menuRepository.queryByAdminId(userDTO.getId()));
    }


}

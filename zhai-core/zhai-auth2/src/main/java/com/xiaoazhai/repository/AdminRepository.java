package com.xiaoazhai.repository;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoazhai.domain.entity.AdminEntity;
import com.xiaoazhai.domain.entity.AdminRoleEntity;
import com.xiaoazhai.domain.entity.RoleEntity;
import com.xiaoazhai.repository.entity.AdminRole;
import com.xiaoazhai.repository.entity.Role;
import com.xiaoazhai.repository.service.AdminRoleService;
import com.xiaoazhai.repository.service.AdminService;
import com.xiaoazhai.util.BeanUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jiangyun
 * @date 2021/9/20  20:41
 **/
@Component
public class AdminRepository {

    @Resource
    private AdminService adminService;
    @Resource
    private AdminRoleService adminRoleService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private RoleRepository roleRepository;
    @Resource
    private MenuRepository menuRepository;

    public IPage<AdminEntity> queryAdminList(Page page, String name) {
        IPage<AdminEntity> adminPage = adminService.queryAdminList(page, name);
        List<AdminEntity> recordList = adminPage.getRecords();
        recordList.forEach(record -> record.setRoleEntityList(roleRepository.queryByAdminId(record.getId())));
        return adminPage;
    }

    public void saveAdmin(AdminEntity adminEntity) {
        Assert.isNull(this.queryAdminByUsername(adminEntity.getUsername()), "用户名冲突！");
        adminEntity.setPassword(passwordEncoder.encode(adminEntity.getPassword()));
        adminService.save(adminEntity.generateDO());
    }

    public void updateAdminById(AdminEntity adminEntity) {
        Assert.isNull(adminService.queryAdminByUsernameAndNeId(adminEntity.getUsername(), adminEntity.getId()), "用户名冲突！");
        adminService.updateById(adminEntity.generateDO());
    }

    public AdminEntity queryAdminById(Long id) {
        return BeanUtil.doToEntity(adminService.getById(id) );
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteAdminById(Long id) {
        adminRoleService.removeByAdminId(id);
        adminService.removeById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void distributionAdminRole(Long adminId, List<AdminRoleEntity> adminRoleEntityList) {
        adminRoleService.removeByAdminId(adminId);
        adminRoleService.saveBatch(BeanUtil.entityToDOBatch(adminRoleEntityList));
    }

    public AdminEntity queryAdminByUsername(String username) {
        AdminEntity adminEntity = BeanUtil.doToEntity(adminService.queryAdminByUsername(username) );
        if (adminEntity != null) {
            adminEntity.setMenuList(menuRepository.queryByAdminId(adminEntity.getId()));
            adminEntity.setRoleEntityList(roleRepository.queryByAdminId(adminEntity.getId()));
        }
        return adminEntity;
    }

    public void changePassword(Long adminId, String password) {
        adminService.changePassword(adminId, passwordEncoder.encode(password));
    }
}

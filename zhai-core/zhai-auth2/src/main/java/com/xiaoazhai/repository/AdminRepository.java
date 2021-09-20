package com.xiaoazhai.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoazhai.domain.entity.AdminEntity;
import com.xiaoazhai.domain.entity.AdminRoleEntity;
import com.xiaoazhai.repository.entity.AdminRole;
import com.xiaoazhai.repository.service.AdminRoleService;
import com.xiaoazhai.repository.service.AdminService;
import com.xiaoazhai.util.BeanUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
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


    public IPage<AdminEntity> queryAdminList(Page page, String name) {
        IPage<AdminEntity> adminPage = adminService.queryAdminList(page, name);
        List<AdminEntity> record = adminPage.getRecords();
        List<Long> adminIdList = record.stream()
                .map(AdminEntity::getId)
                .collect(Collectors.toList());
        List<AdminRole> adminRoleList = adminRoleService.queryByAdminIdList(adminIdList);
        Map<Long, List<Long>> adminRoleMap = adminRoleList.stream()
                .collect(Collectors.groupingBy(AdminRole::getAdminId, Collectors.mapping(AdminRole::getRoleId, Collectors.toList())));
        record.forEach(admin -> admin.setRoleIdList(adminRoleMap.get(admin.getId())));
        return adminPage;
    }

    public void saveAdmin(AdminEntity adminEntity) {
        adminEntity.setPassword(passwordEncoder.encode(adminEntity.getPassword()));
        adminService.save(adminEntity.generateDO());
    }

    public void updateAdminById(AdminEntity adminEntity) {
        adminService.updateById(adminEntity.generateDO());
    }

    public AdminEntity queryAdminById(Long id) {
        return BeanUtil.doToEntity(adminService.getById(id), AdminEntity.class);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteAdminById(Long id) {
        adminRoleService.removeByAdminId(id);
        adminService.removeById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void distributionAdminRole(Long adminId, List<AdminRoleEntity> adminRoleEntityList) {
        adminRoleService.removeByAdminId(adminId);
        adminRoleService.saveBatch(BeanUtil.entityToDOBatch(adminRoleEntityList, AdminRole.class));
    }
}

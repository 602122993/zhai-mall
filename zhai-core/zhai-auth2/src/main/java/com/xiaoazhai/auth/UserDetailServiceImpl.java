package com.xiaoazhai.auth;

import com.xiaoazhai.domain.entity.AdminEntity;
import com.xiaoazhai.domain.entity.RoleEntity;
import com.xiaoazhai.repository.AdminRepository;
import com.xiaoazhai.util.BeanUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.stream.Collectors;

/**
 * @author jiangyun
 * @date 2021/9/14  16:25
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AdminEntity adminEntity = adminRepository.queryAdminByUsername(s);
        if (adminEntity == null) {
            return null;
        }

        AuthUser authUser = new AuthUser();
        authUser.setUserDTO(convertFromEntity(adminEntity));
        return authUser;
    }


    public static UserDTO convertFromEntity(AdminEntity adminEntity) {
        UserDTO result = BeanUtil.copyPropertiesIgnoreNullValue(adminEntity, UserDTO.class);
        result.setRoleIdList(adminEntity.getRoleEntityList().stream()
                .map(RoleEntity::getId)
                .collect(Collectors.toList()));
        return result;
    }
}

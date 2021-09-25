package com.xiaoazhai.auth;

import com.xiaoazhai.domain.entity.AdminEntity;
import com.xiaoazhai.repository.AdminRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
        authUser.setUserDTO(UserDTO.convertFromEntity(adminEntity));
        return authUser;
    }
}

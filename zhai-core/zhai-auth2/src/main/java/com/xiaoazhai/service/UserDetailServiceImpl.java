package com.xiaoazhai.service;

import com.xiaoazhai.entity.SpringUser;
import com.xiaoazhai.entity.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jiangyun
 * @date 2021/9/14  16:25
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword(passwordEncoder.encode("123456"));
        userDTO.setUsername("user");
        SpringUser springUser = new SpringUser();
        springUser.setUserDTO(userDTO);
        return springUser;
    }
}

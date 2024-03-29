package com.xiaoazhai.auth;

import com.xiaoazhai.domain.entity.AdminEntity;
import com.xiaoazhai.enums.CommonStatusEnum;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author jiangyun
 * @date 2021/9/14  16:26
 **/
@Data
public class AuthUser implements UserDetails {

    private UserDTO userDTO;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDTO.getRoleIdList()
                .stream()
                .map(id -> new SimpleGrantedAuthority(id.toString()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return userDTO.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userDTO.getStatus() == CommonStatusEnum.USED.getCode();
    }
}

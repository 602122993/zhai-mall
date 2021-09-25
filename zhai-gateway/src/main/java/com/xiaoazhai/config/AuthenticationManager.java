package com.xiaoazhai.config;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiangyun
 * @date 2021/9/24  08:50
 **/
@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToekn = authentication.getCredentials().toString();
        try {
//             claims= Jwt.parseJwt(authToekn);
            //todo 此处应该列出token中携带的角色表。
            List<String> roles = new ArrayList();
            roles.add("user");
//            Authentication authentication1=new UsernamePasswordAuthenticationToken(
//                    claims.getId(),
//                    null,
//                    roles.stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList())
//            );
            return Mono.just(new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), ""));
        } catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }
}
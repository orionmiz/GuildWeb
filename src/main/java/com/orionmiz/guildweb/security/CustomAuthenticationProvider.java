package com.orionmiz.guildweb.security;

import lombok.extern.java.Log;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Log
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    final SecurityMembersService service;

    public CustomAuthenticationProvider(SecurityMembersService service) {
        this.service = service;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userId = authentication.getPrincipal().toString();
        String userPw = authentication.getCredentials().toString();

        log.info("!!! ID : " + userId + ", PW : " + userPw + " !!!");

        SecurityMember member = service.loadUserByUsername(userId);

        if (member == null) {
            throw new UsernameNotFoundException(userId);
        }

        if (member.getPassword().equals(userPw)) {
            if (!member.isEnabled()) {
                throw new DisabledException(userId);
            }
        } else {
            throw new BadCredentialsException(userId);
        }

        return new UsernamePasswordAuthenticationToken(userId, userPw, member.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


}

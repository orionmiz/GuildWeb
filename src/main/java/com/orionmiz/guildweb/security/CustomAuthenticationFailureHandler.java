package com.orionmiz.guildweb.security;

import lombok.extern.java.Log;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info("!!! 여기야 !!!");
        e.printStackTrace();
        int err_code = 0;
        if (e instanceof BadCredentialsException) {
            err_code = 1;
        } else if (e instanceof DisabledException) {
            err_code = 2;
        } else if (e instanceof UsernameNotFoundException){
            err_code = 3;
        } else {
            err_code = 4;
        }

        log.info("!!! 에러 : " + e.getMessage() + " !!!");

        httpServletResponse.sendRedirect("/login?error=" + err_code);
    }
}

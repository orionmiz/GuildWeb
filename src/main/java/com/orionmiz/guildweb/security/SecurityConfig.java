package com.orionmiz.guildweb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final SecurityMembersService service;
    final CustomAuthenticationProvider authenticationProvider;
    final CustomAuthenticationFailureHandler failureHandler;

    public SecurityConfig(SecurityMembersService service, CustomAuthenticationProvider provider, CustomAuthenticationFailureHandler failureHandler) {
        this.service = service;
        this.authenticationProvider = provider;
        this.failureHandler = failureHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/home").permitAll()
                .antMatchers("/boards/**").permitAll();

        http.formLogin().loginPage("/login").failureHandler(failureHandler);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(service).passwordEncoder(NoOpPasswordEncoder.getInstance());
        auth.authenticationProvider(authenticationProvider);
    }
}

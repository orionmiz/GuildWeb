package com.orionmiz.guildweb.security;

import org.springframework.security.core.AuthenticationException;

public class Usern extends AuthenticationException {
    public Usern(String msg) {
        super(msg);
    }
}

package com.orionmiz.guildweb.security;

import com.orionmiz.guildweb.persistence.MemberRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Log
@Service
public class SecurityMembersService implements UserDetailsService {

    final MemberRepository repo;

    public SecurityMembersService(MemberRepository repo) {
        this.repo = repo;
    }

    @Override
    public SecurityMember loadUserByUsername(String s) throws UsernameNotFoundException {
        return repo.findById(s).map(SecurityMember::new).orElse(null);
    }
}

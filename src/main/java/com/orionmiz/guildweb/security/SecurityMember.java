package com.orionmiz.guildweb.security;

import com.orionmiz.guildweb.domain.Member;
import com.orionmiz.guildweb.domain.MemberRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
public class SecurityMember extends User {

    private boolean enabled;

    public SecurityMember(Member member) {
        super(member.getUsername(), member.getPassword(), makeGrantedAuthorities(member.getRoles()));
        this.enabled = member.isEnabled();
    }

    public SecurityMember(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    private static List<SimpleGrantedAuthority> makeGrantedAuthorities(List<MemberRole> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        roles.forEach( role -> { list.add(new SimpleGrantedAuthority(role.getRoleName())); } );
        return list;
    }
}

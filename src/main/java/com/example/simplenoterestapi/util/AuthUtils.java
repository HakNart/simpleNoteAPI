package com.example.simplenoterestapi.util;

import com.example.simplenoterestapi.model.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AuthUtils {

    public static List<SimpleGrantedAuthority> generateSimpleGrantedAuthorities(final Set<Role> userRoles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role: userRoles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}

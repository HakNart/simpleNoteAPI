package com.example.simplenoterestapi.service;

import com.example.simplenoterestapi.dto.SecurityUser;
import com.example.simplenoterestapi.model.User;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailService implements UserDetailsService {
    private final UserService userService;

    public SecurityUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User with email " + email + " not found in the database");
        }
        return createSecurityUser(user);
    }

    private UserDetails createSecurityUser(User user) {
        return new SecurityUser(user);
    }
}

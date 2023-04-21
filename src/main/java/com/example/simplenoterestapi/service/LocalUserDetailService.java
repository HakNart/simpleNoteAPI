package com.example.simplenoterestapi.service;

import com.example.simplenoterestapi.dto.LocalUser;
import com.example.simplenoterestapi.model.User;
import com.example.simplenoterestapi.util.AuthUtils;
import jakarta.transaction.Transactional;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class LocalUserDetailService implements UserDetailsService {
    private final UserService userService;

    public LocalUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public LocalUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User with email " + email + " not found in the database");
        }
        return createLocalUser(user);
    }

    private LocalUser createLocalUser(User user) {
        return new LocalUser(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, AuthUtils.generateSimpleGrantedAuthorities(user.getRoles()));
    }
}

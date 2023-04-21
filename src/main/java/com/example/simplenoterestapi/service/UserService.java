package com.example.simplenoterestapi.service;

import com.example.simplenoterestapi.dto.RegisterRequest;
import com.example.simplenoterestapi.exception.UserAlreadyExistAuthenticationException;
import com.example.simplenoterestapi.model.Role;
import com.example.simplenoterestapi.model.User;
import com.example.simplenoterestapi.repository.RoleRepository;
import com.example.simplenoterestapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findUserByEmail(final String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    @Transactional
    public User registerNewUser(RegisterRequest registerRequest) throws UserAlreadyExistAuthenticationException {
        if (userRepository.existsByEmailIgnoreCase(registerRequest.getEmail())) {
            throw new UserAlreadyExistAuthenticationException("User with email id " + registerRequest.getEmail() + " already exist");
        }
        User user = buildUser(registerRequest);
        user = userRepository.save(user);
        userRepository.flush();
        return user;
    }

    private User buildUser(final RegisterRequest registerRequest) {
        User user = User.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .roles(Set.of(roleRepository.findByName(Role.ROLE_USER)))
                .username(registerRequest.getUsername())
                .enabled(true)
                .build();

        return user;
    }
}

package com.example.simplenoterestapi.service;

import com.example.simplenoterestapi.model.User;
import com.example.simplenoterestapi.repository.RoleRepository;
import com.example.simplenoterestapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    public User findUserByEmail(final String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }
}

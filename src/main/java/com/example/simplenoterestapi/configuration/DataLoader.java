package com.example.simplenoterestapi.configuration;

import com.example.simplenoterestapi.model.Role;
import com.example.simplenoterestapi.model.User;
import com.example.simplenoterestapi.repository.RoleRepository;
import com.example.simplenoterestapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private boolean alreadySetup = false;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (alreadySetup || userRepository.findAll().iterator().hasNext()) {
            return;
        }

        // Create user roles
        Role userRole = createRoleIfNotFound(Role.ROLE_USER);
        Role adminRole = createRoleIfNotFound(Role.ROLE_ADMIN);

        // Create users
        createUserIfNotFound("user@email.com", passwordEncoder.encode("user123"), Set.of(userRole), "User");
        createUserIfNotFound("admin@email.com", passwordEncoder.encode("admin123"), Set.of(adminRole, userRole), "Administrator");
        alreadySetup = true;
    }

    @Transactional
    void createUserIfNotFound(String email, String password, Set<Role> roles, String username) {
        User user = userRepository.findByEmailIgnoreCase(email);
        if (user == null) {
            user = User.builder()
                    .email(email)
                    .password(password)
                    .roles(roles)
                    .username(username)
                    .enabled(true)
                    .build();
            userRepository.save(user);
        }
    }
    @Transactional
    Role createRoleIfNotFound(String roleName) {
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            role = Role.builder()
                    .name(roleName)
                    .build();
            role = roleRepository.save(role);
        }
        return role;
    }
}

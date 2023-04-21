package com.example.simplenoterestapi.repository;

import com.example.simplenoterestapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);
}

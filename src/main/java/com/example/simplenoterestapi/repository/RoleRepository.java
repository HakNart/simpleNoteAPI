package com.example.simplenoterestapi.repository;

import com.example.simplenoterestapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("""
    select r from Role r where r.name = :name
    """)
    Role findByName(String name);
}

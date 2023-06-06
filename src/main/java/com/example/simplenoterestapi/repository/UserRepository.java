package com.example.simplenoterestapi.repository;

import com.example.simplenoterestapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("""
        SELECT u from User u where upper(u.email) = upper(:email)
    """)
    User findByEmailIgnoreCase(String email);

    @Query("""
        select case when count(u) > 0 then true else false end
        from  User u where upper(u.email) = upper(:email)
    """)
    boolean existsByEmailIgnoreCase(String email);
}

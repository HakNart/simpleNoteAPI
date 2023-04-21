package com.example.simplenoterestapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="_user")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {

    @NaturalId(mutable = true)
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotNull
    private String password;

    @Column(name = "username")
    private String username;

    private boolean enabled;

    private String secret;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "UserRole",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "id")}
    )
    private Set<Role> roles = new HashSet<>();

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void addRole(Role role) {
        roles.add(role);
    }
}

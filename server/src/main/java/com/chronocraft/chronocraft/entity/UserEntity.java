package com.chronocraft.chronocraft.entity;

import com.chronocraft.chronocraft.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email is mandatory")
    private String email;
    private String password;
    private String name;
    @Enumerated(EnumType.STRING)
    private UserRole role;
}

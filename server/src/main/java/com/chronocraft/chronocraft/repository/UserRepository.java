package com.chronocraft.chronocraft.repository;

import com.chronocraft.chronocraft.entity.UserEntity;
import com.chronocraft.chronocraft.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findFirstByEmail(String username);
    UserEntity findByRole(UserRole userRole);
}

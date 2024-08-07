package com.chronocraft.ChronoCraft.repository;

import com.chronocraft.ChronoCraft.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
}

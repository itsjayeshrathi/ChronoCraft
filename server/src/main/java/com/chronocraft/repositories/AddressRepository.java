package com.chronocraft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chronocraft.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}

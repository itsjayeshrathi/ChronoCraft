package com.chronocraft.ChronoCraft.repository;

import com.chronocraft.ChronoCraft.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,Long> {
}

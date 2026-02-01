package com.example.clientapp.repository;

import com.example.clientapp.model.Boss;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BossRepository extends JpaRepository<Boss, Integer> {

    Optional<Boss> findByUsername(String username);
}

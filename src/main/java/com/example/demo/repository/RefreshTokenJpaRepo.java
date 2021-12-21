package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.RefreshToken;

public interface RefreshTokenJpaRepo extends JpaRepository<RefreshToken, String> {

    Optional<RefreshToken> findByKeyId(Long key);
}

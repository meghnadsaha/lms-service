package com.example.lmsservice.repositories;

import com.example.lmsservice.models.RefreshToken;
import com.example.lmsservice.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken ( String token );

    Optional<RefreshToken> findByUserInfo ( UserInfo userInfo );
}

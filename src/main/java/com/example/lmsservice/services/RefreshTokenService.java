package com.example.lmsservice.services;


import com.example.lmsservice.exception.TokenExpiredException;
import com.example.lmsservice.exception.UserInfoNotFoundException;
import com.example.lmsservice.models.RefreshToken;
import com.example.lmsservice.models.UserInfo;
import com.example.lmsservice.repositories.RefreshTokenRepository;
import com.example.lmsservice.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;

    public RefreshToken createRefreshToken(String username) throws UserInfoNotFoundException {
        Optional<UserInfo> userInfoOptional = userInfoRepository.findByName(username);
        if (userInfoOptional.isPresent()) {
            UserInfo userInfo = userInfoOptional.get();
            Optional<RefreshToken> existingTokenOptional = refreshTokenRepository.findByUserInfo(userInfo);
            RefreshToken refreshToken;
            if (existingTokenOptional.isPresent()) {
                refreshToken = existingTokenOptional.get();
                refreshToken.setToken(UUID.randomUUID().toString());
                refreshToken.setExpiryDate(Instant.now().plusMillis(600000));

            } else {
                refreshToken = RefreshToken.builder()
                                           .userInfo(userInfo)
                                           .token(UUID.randomUUID().toString())
                                           .expiryDate(Instant.now().plusMillis(600000))
                                           .build();
            }
            return refreshTokenRepository.save(refreshToken);
        } else {
            // Handle case where userInfo is not found
            throw new UserInfoNotFoundException("User Info not found for username: " + username);
        }
    }



    public Optional<RefreshToken> findByToken ( String token ) {
        return refreshTokenRepository.findByToken(token);
    }


    public RefreshToken verifyExpiration ( RefreshToken token ) throws TokenExpiredException {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenExpiredException(
                    token.getToken() + " Refresh token was expired. Please make a new signin request");
        }
        return token;
    }


    public void deleteToken ( RefreshToken token ) {
        refreshTokenRepository.delete(token);
    }

}

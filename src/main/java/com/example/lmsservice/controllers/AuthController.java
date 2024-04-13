package com.example.lmsservice.controllers;


import com.example.lmsservice.dto.*;
import com.example.lmsservice.exception.TokenExpiredException;
import com.example.lmsservice.exception.UserInfoNotFoundException;
import com.example.lmsservice.mapper.UserMapper;
import com.example.lmsservice.models.RefreshToken;
import com.example.lmsservice.models.UserInfo;
import com.example.lmsservice.services.JwtService;
import com.example.lmsservice.services.ProductService;
import com.example.lmsservice.services.RefreshTokenService;
import com.example.lmsservice.services.UserInfoUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin(origins = "*") // Allow requests from all domains
@CrossOrigin(origins = "http://localhost:8080")
public class AuthController {

    @Autowired
    private ProductService service;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private UserInfoUserDetailsService userInfoUserDetailsService;


//    @PostMapping("/signUp")
//    public String addNewUser ( @RequestBody UserInfo userInfo ) {
//        return service.addUser(userInfo);
//    }

    @PostMapping("/signUp")
    public ResponseEntity<String> addNewUser( @Valid @RequestBody SaveUserDTO saveUserDTO, BindingResult result) {


        // Map SaveUserDTO to UserInfo and call service method to add user
        UserInfo userInfo = UserMapper.INSTANCE.mapToUserInfo(saveUserDTO);

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid request body");
        }

        if (userInfoUserDetailsService.existsByUsername(userInfo.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        if (userInfoUserDetailsService.existsByEmail(userInfo.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }

        String message = service.addUser(userInfo);
        return ResponseEntity.ok(message);
    }



    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Product> getAllTheProducts () {
        return service.getProducts();
    }


    @GetMapping("/admin/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Product getProductById ( @PathVariable int id ) {
        return service.getProduct(id);
    }


    @PostMapping("/login")
    public JwtResponse authenticateAndGetToken ( @RequestBody AuthRequest authRequest ) throws UserInfoNotFoundException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername() , authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequest.getUsername());
            return JwtResponse.builder()
                              .accessToken(jwtService.generateToken(authRequest.getUsername()))
                              .token(refreshToken.getToken())
                              .roles(userInfoUserDetailsService.findRolesByName(authRequest.getUsername()))
                              .build();
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

//    @PostMapping("/refreshToken")
//    public JwtResponse refreshToken ( @RequestBody RefreshTokenRequest refreshTokenRequest ) {
//        return refreshTokenService.findByToken(refreshTokenRequest.getToken())
//                                  .map(refreshTokenService::verifyExpiration)
//                                  .map(RefreshToken::getUserInfo)
//                                  .map(userInfo -> {
//                                      String accessToken = jwtService.generateToken(userInfo.getName());
//                                      return JwtResponse.builder()
//                                                        .accessToken(accessToken)
//                                                        .token(refreshTokenRequest.getToken())
//                                                        .build();
//                                  }).orElseThrow(() -> new RuntimeException(
//                        "Refresh token is not in database!"));
//    }

    @PostMapping("/refreshToken")
    public JwtResponse refreshToken ( @RequestBody RefreshTokenRequest refreshTokenRequest ) throws TokenExpiredException {
        Optional<RefreshToken> optionalToken = refreshTokenService.findByToken(refreshTokenRequest.getToken());
        if (optionalToken.isPresent()) {
            RefreshToken refreshToken = optionalToken.get();
            try {
                refreshTokenService.verifyExpiration(refreshToken);
                UserInfo userInfo = refreshToken.getUserInfo();
                String accessToken = jwtService.generateToken(userInfo.getName());
                return JwtResponse.builder().accessToken(accessToken).token(refreshTokenRequest.getToken()).build();
            } catch (TokenExpiredException e) {
                refreshTokenService.deleteToken(refreshToken);
                throw new TokenExpiredException(
                        refreshToken.getToken() + " Refresh token was expired. Please make a new signin request");
            }
        } else {
            throw new TokenExpiredException("Refresh token is not in database!");
        }
    }


}

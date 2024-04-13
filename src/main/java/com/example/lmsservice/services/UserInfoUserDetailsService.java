package com.example.lmsservice.services;


import com.example.lmsservice.config.UserInfoUserDetails;
import com.example.lmsservice.models.UserInfo;
import com.example.lmsservice.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername ( String username ) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userInfoRepository.findByName(username);
        return userInfo.map(UserInfoUserDetails::new)
                       .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }

    public List<SimpleGrantedAuthority> findRolesByName ( String name ) {
        Optional<UserInfo> userInfo = userInfoRepository.findByName(name);
//        return userInfo.map(user -> Arrays.stream(user.getRoles() .split(","))
        return userInfo.map(user -> user.getRoles().stream()
                                        .map(SimpleGrantedAuthority::new)
                                        .collect(Collectors.toList()))
                       .orElseThrow(() -> new UsernameNotFoundException("User not found: " + name));
    }


    public boolean existsByUsername ( String username ) {
        return userInfoRepository.findByName(username).isPresent();
    }

    public boolean existsByEmail ( String email ) {
        return userInfoRepository.findByEmail(email).isPresent();
    }
}

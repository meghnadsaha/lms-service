package com.example.lmsservice.repositories;

import com.example.lmsservice.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByName ( String username );

    @Query("SELECT u.roles FROM UserInfo u WHERE u.name = :name")
    Set<String> findRolesByName( @Param("name") String name);

    Optional<UserInfo> findByEmail ( String email );
}

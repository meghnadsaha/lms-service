package com.example.lmsservice.repositories;


import com.example.lmsservice.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername ( String username );

    User saveUser ( User user );

    Optional<List<User>> findByUserRole ( String userRole );
}

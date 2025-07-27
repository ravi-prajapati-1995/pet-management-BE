package com.pet.management.repository;

import com.pet.management.model.auth.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);
}

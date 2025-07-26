package com.pet.management.repository;

import com.pet.management.model.auth.User;

public interface UserRepository {
    public User findByUsername(String username);
}

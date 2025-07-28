package com.pet.management.service;

import com.pet.management.repository.UserRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import lombok.extern.slf4j.Slf4j;

import static org.mindrot.jbcrypt.BCrypt.checkpw;

@Singleton
@Slf4j
public class AuthService {
    @EJB
    private UserRepository userRepository;

    public boolean authenticate(String username, String password) {
        log.debug("login with username: {}", username);
        final var userOptional = userRepository.findByUsername(username);
        return userOptional.filter(user -> checkpw(password, user.getPassword())).isPresent();
    }
}

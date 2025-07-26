package com.pet.management.service;

import com.pet.management.model.auth.User;
import com.pet.management.repository.UserRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;

import static org.mindrot.jbcrypt.BCrypt.checkpw;

@Stateless
@Slf4j
public class AuthService {
    @EJB
    private UserRepository userRepository;

    public boolean authenticate(String username, String password) {
        log.debug("login with username: {} and password: {}", username, password);

        User user = userRepository.findByUsername(username);
        if (user == null) return false;

        return checkpw(password, user.getPassword());
    }
}

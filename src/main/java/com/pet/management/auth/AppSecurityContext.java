package com.pet.management.auth;

import jakarta.ws.rs.core.SecurityContext;

import java.security.Principal;

public class AppSecurityContext implements SecurityContext {
    private final String username;

    public AppSecurityContext(String username) {
        this.username = username;
    }

    @Override
    public Principal getUserPrincipal() {
        return () -> username;
    }

    @Override
    public boolean isUserInRole(String role) {
        // Optional: Implement role logic
        return true;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getAuthenticationScheme() {
        return "Bearer";
    }
}


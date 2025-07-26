package com.pet.management.auth.filter;

import com.pet.management.auth.AppSecurityContext;
import jakarta.annotation.Priority;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.pet.management.auth.JwtUtil.validateToken;
import static jakarta.ws.rs.Priorities.AUTHENTICATION;
import static jakarta.ws.rs.core.HttpHeaders.AUTHORIZATION;

@Provider
@Priority(AUTHENTICATION)
@Slf4j
public class JwtAuthFilter implements ContainerRequestFilter {
    private static final String LOGIN_PATH = "/auth/login";

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String path = requestContext.getUriInfo().getPath();
        log.debug("Got request for: {}", path);
        if (LOGIN_PATH.equalsIgnoreCase(path)) {
            return; // skip JWT validation
        }

        String authHeader = requestContext.getHeaderString(AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }

        String token = authHeader.substring("Bearer".length()).trim();
        try {
            String user = validateToken(token);
            requestContext.setSecurityContext(new AppSecurityContext(user));
        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}

package com.pet.management.resource.auth;

import com.pet.management.auth.JwtUtil;
import com.pet.management.dto.auth.LoginRequest;
import com.pet.management.service.AuthService;
import jakarta.ejb.EJB;
import jakarta.persistence.NoResultException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.util.Map;

import static com.pet.management.auth.JwtUtil.generateToken;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.*;

@Path("/auth")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class AuthResource {
    @EJB
    private AuthService authService;

    @POST
    @Path("/login")
    public Response login(LoginRequest request) {
        String username = request.username;
        String password = request.password;
        try {
            if (authService.authenticate(username, password)) {
                String token = generateToken(username);
                return Response.ok().entity(Map.of("token", token)).build();
            }

            return Response.status(UNAUTHORIZED)
                    .entity(Map.of("error", "Invalid credentials"))
                    .build();
        } catch (NoResultException e) {
            return Response.status(UNAUTHORIZED)
                    .entity(Map.of("error", "Invalid credentials"))
                    .build();
        }
    }
}

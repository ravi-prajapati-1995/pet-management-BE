package com.pet.management.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import lombok.extern.slf4j.Slf4j;

import static com.pet.management.config.AppConstants.PET_APP_V1;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/pets")
@Produces(APPLICATION_JSON)
@Consumes(PET_APP_V1)
@RolesAllowed("ROLE_ADMIN")
@Slf4j
public class VaccineResource {

}

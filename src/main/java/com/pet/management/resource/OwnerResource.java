package com.pet.management.resource;

import com.pet.management.dto.update.OwnerUpdateDTO;
import com.pet.management.service.OwnerService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import static com.pet.management.config.AppConstants.PET_APP_V1;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/owner")
@Produces(APPLICATION_JSON)
@Consumes(PET_APP_V1)
@RolesAllowed("ROLE_ADMIN")
@Slf4j
public class OwnerResource {
    @EJB
    private OwnerService ownerService;

    @PATCH
    @Path("/{id}")
    public Response updateOwner(@PathParam("id") Long id, OwnerUpdateDTO dto) {
        try {
            ownerService.updateOwnerDetails(id, dto);
            return Response.noContent().build();
        } catch (NotFoundException exception) {
            log.error("Owner not found with id: {}", id);
            return Response.status(NOT_FOUND).build();
        }
    }

}

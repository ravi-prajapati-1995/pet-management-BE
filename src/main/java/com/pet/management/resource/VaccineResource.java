package com.pet.management.resource;

import com.pet.management.dto.UpdateVaccineDTO;
import com.pet.management.service.VaccineService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.pet.management.constants.AppConstants.PET_APP_V1;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/pet/{id}/vaccine")
@Produces(APPLICATION_JSON)
@Consumes(PET_APP_V1)
@RolesAllowed("ROLE_ADMIN")
@Slf4j
public class VaccineResource {

    @EJB
    private VaccineService vaccineService;


    @GET
    public Response getVaccine(@PathParam("id") Long petId) {
        final var vaccines = vaccineService.getByPetId(petId);
        return Response.ok(vaccines).build();
    }

    @PUT
    public Response updateVaccine(@PathParam("id") Long id, List<UpdateVaccineDTO> updatedVaccine) {
        vaccineService.updateVaccineDetails(id, updatedVaccine);
        return Response.noContent().build();
    }
}

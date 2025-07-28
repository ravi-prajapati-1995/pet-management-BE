package com.pet.management.resource;

import com.pet.management.dto.PetDetailsDTO;
import com.pet.management.dto.update.PetUpdateDto;
import com.pet.management.model.Pet;
import com.pet.management.service.PetService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.pet.management.constants.AppConstants.PET_APP_V1;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/pets")
@Produces(APPLICATION_JSON)
@Consumes(PET_APP_V1)
@RolesAllowed("ROLE_ADMIN")
@Slf4j
public class PetResource {
    @EJB
    private PetService petService;

    @GET
    public List<PetDetailsDTO> getAllPets() {
        return petService.getAllPets();
    }

    @GET
    @Path("/search")
    public List<PetDetailsDTO> search(@QueryParam("name") String name) {
        return petService.searchByName(name);
    }

    @PATCH
    @Path("/{id}")
    public Response updatePet(@PathParam("id") Long id, PetUpdateDto dto) {
        try {
            petService.updatePet(id, dto);
            return Response.noContent().build();
        } catch (NotFoundException exception) {
            log.error("Pet not found with id: {}", id);
            return Response.status(NOT_FOUND).build();
        }
    }

    @POST
    public void save(Pet pet) {
        petService.savePet(pet);
    }
}

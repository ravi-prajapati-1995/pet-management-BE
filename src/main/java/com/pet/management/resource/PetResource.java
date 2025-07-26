package com.pet.management.resource;

import com.pet.management.dto.PetDetailsDTO;
import com.pet.management.model.Pet;
import com.pet.management.service.PetService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/pets")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@RolesAllowed("ROLE_ADMIN")
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

    @POST
    public void save(Pet pet) {
        petService.savePet(pet);
    }
}

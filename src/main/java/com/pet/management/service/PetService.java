package com.pet.management.service;

import com.pet.management.dto.PetDetailsDTO;
import com.pet.management.dto.update.PetUpdateDto;
import com.pet.management.model.Pet;
import com.pet.management.repository.PetRepository;
import jakarta.ejb.Singleton;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@Singleton
public class PetService {
    @Inject
    private PetRepository petRepository;

    public List<PetDetailsDTO> getAllPets() {
        return petRepository.findAll();
    }

    public List<PetDetailsDTO> searchByName(String name) {
        return petRepository.findByName(name);
    }

    public void updatePet(Integer id, PetUpdateDto petUpdateDto) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pet not found"));

        final var updatedPet = pet.toBuilder()
                .name(petUpdateDto.getName())
                .age(pet.getAge())
                .build();

        petRepository.save(updatedPet);
    }


    public void savePet(Pet pet) {
        petRepository.save(pet);
    }
}

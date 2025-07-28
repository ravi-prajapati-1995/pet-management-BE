package com.pet.management.service;

import com.pet.management.dto.PetDetailsDTO;
import com.pet.management.dto.update.PetUpdateDto;
import com.pet.management.model.Pet;
import com.pet.management.repository.PetRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Singleton
@Slf4j
public class PetService {
    @EJB
    private PetRepository petRepository;
    @EJB
    private VaccineService vaccineService;

    public List<PetDetailsDTO> getAllPets() {
        return petRepository.findAll();
    }

    public List<PetDetailsDTO> searchByName(String name) {
        return petRepository.findByName(name);
    }

    public void updatePet(Long id, PetUpdateDto petUpdateDto) {
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

    public Optional<Pet> findById(final Long id) {
        return petRepository.findById(id);
    }

    public List<PetDetailsDTO> getVaccinePendingPets() {
        final var vaccinePendingPetIds = vaccineService.getVaccinePendingPetIds();
        return petRepository.findByIds(vaccinePendingPetIds);
    }
}

package com.pet.management.service;

import com.pet.management.dto.PetDetailsDTO;
import com.pet.management.model.Pet;
import com.pet.management.repository.PetRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class PetService {
    @Inject
    private PetRepository petRepository;

    public List<PetDetailsDTO> getAllPets() {
        return petRepository.findAll();
    }

    public List<PetDetailsDTO> searchByName(String name) {
        return petRepository.findByName(name);
    }

    public void savePet(Pet pet) {
        petRepository.save(pet);
    }
}

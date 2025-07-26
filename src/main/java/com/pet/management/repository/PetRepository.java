package com.pet.management.repository;

import com.pet.management.dto.PetDetailsDTO;
import com.pet.management.model.Pet;

import java.util.List;

public interface PetRepository {
    Pet findById(int id);
    List<PetDetailsDTO> findAll();
    List<PetDetailsDTO> findByName(String name);
    void save(Pet pet);
    void delete(int id);
}

package com.pet.management.repository;

import com.pet.management.dto.PetDetailsDTO;
import com.pet.management.model.Pet;

import java.util.List;
import java.util.Optional;

public interface PetRepository {
    Optional<Pet> findById(Long id);
    List<PetDetailsDTO> findAll();
    List<PetDetailsDTO> findByName(String name);
    void save(Pet pet);
    void delete(Long id);
    List<PetDetailsDTO> findByIds(List<Long> petIds);
}

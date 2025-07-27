package com.pet.management.repository;

import com.pet.management.model.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository {
    void save(Owner owner);
    Optional<Owner> findById(Long id);
    List<Owner> findAll();
    void delete(Long id);
    List<Owner> findByName(String name);
}

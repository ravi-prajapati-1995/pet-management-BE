package com.pet.management.repository;

import com.pet.management.model.Owner;

import java.util.List;

public interface OwnerRepository {
    void save(Owner owner);
    Owner findById(int id);
    List<Owner> findAll();
    void delete(int id);
    List<Owner> findByName(String name);
}

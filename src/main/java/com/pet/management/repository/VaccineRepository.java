package com.pet.management.repository;

import com.pet.management.model.Vaccine;

import java.util.List;

public interface VaccineRepository {
    List<Vaccine> findByPetId(Long petId);

    void saveAll(List<Vaccine> vaccines);

    int deleteByPetId(Long petId);
    List<Long> getPetIdsWhereVaccineExpire();
}

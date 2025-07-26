package com.pet.management.repository;

import com.pet.management.dto.VaccineDTO;

import java.util.List;

public interface VaccineRepository {
    List<VaccineDTO> findByPetId(int petId);
}

package com.pet.management.service;

import com.pet.management.dto.UpdateVaccineDTO;
import com.pet.management.dto.VaccineDTO;
import com.pet.management.model.Vaccine;
import com.pet.management.repository.VaccineRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@Singleton
public class VaccineService {
    @EJB
    private VaccineRepository vaccineRepository;
    @EJB
    private PetService petService;

    public List<VaccineDTO> getByPetId(final Long petId) {
        final var vaccines = vaccineRepository.findByPetId(petId);
        return vaccines.stream()
                .map(VaccineDTO::from)
                .toList();
    }

    public void updateVaccineDetails(final Long petId, List<UpdateVaccineDTO> updateVaccineDTOS) {

        final var byId = petService.findById(petId);
        if(byId.isEmpty()) {
            throw new NotFoundException("Pet not found with id");
        }

        final var pet = byId.get();
        vaccineRepository.deleteByPetId(petId);

        final var updatedVaccineList = updateVaccineDTOS.stream()
                .map(dto -> Vaccine.from(dto, pet))
                .toList();

        vaccineRepository.saveAll(updatedVaccineList);
    }

    public List<Long> getVaccinePendingPetIds() {
        return vaccineRepository.getPetIdsWhereVaccineExpire();
    }

}

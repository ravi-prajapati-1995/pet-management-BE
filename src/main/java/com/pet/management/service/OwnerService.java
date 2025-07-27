package com.pet.management.service;

import com.pet.management.dto.update.OwnerUpdateDTO;
import com.pet.management.repository.OwnerRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@Stateless
public class OwnerService {
    @Inject
    private OwnerRepository ownerRepository;

    public void updateOwnerDetails(final Long ownerId, final OwnerUpdateDTO ownerUpdateDTO) {
        final var owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new NotFoundException("Pet not found"));

        final var updateOwner = owner.toBuilder()
                .name(ownerUpdateDTO.getOwnerName())
                .phoneNumber(ownerUpdateDTO.getOwnerPhone())
                .build();

        ownerRepository.save(updateOwner);
    }
}

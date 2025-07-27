package com.pet.management.config;

import com.pet.management.messaging.producer.VaccineMessageProducer;
import com.pet.management.service.PetService;
import jakarta.ejb.Schedule;
import jakarta.ejb.Startup;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import static com.pet.management.utils.JsonUtil.asJsonString;

@Stateless
@Startup
@Slf4j
public class VaccineScheduler {

    @Inject
    private VaccineMessageProducer vaccineMessageProducer;

    @Inject
    private PetService petService;

    @Schedule(hour = "*", minute = "*", second = "0", persistent = false)
    public void hourlyTask() {
        final var vaccinePendingPets = petService.getVaccinePendingPets();
        vaccineMessageProducer.sendMessage(asJsonString(vaccinePendingPets));
    }
}

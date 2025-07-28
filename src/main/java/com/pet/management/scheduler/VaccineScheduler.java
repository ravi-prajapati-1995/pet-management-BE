package com.pet.management.scheduler;

import com.pet.management.messaging.producer.VaccineMessageProducer;
import com.pet.management.service.PetService;
import jakarta.ejb.*;
import lombok.extern.slf4j.Slf4j;

import static com.pet.management.utils.JsonUtil.asJsonString;

@Singleton
@Startup
@Slf4j
public class VaccineScheduler {
    @EJB
    private VaccineMessageProducer vaccineMessageProducer;

    @EJB
    private PetService petService;

    @Schedule(hour = "*", minute = "*", second = "15", persistent = false)
    public void perMinuteTask() {
        final var vaccinePendingPets = petService.getVaccinePendingPets();
        final var jsonString = asJsonString(vaccinePendingPets);
        vaccineMessageProducer.sendMessage(jsonString);
    }

    @Timeout
    public void timeoutHandler(Timer timer) {
        // NO handling
    }
}

package com.pet.management.dto;

import com.pet.management.model.Vaccine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class VaccineDTO {
    private int id;
    private String vaccineName;
    private LocalDateTime vaccinationTime;

    public static VaccineDTO from(Vaccine vaccine) {
        if (vaccine == null) return null;
        return VaccineDTO.builder()
                .id(vaccine.getId())
                .vaccineName(vaccine.getVaccineName())
                .vaccinationTime(vaccine.getVaccinationTime())
                .build();
    }
}

package com.pet.management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Long id;
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime time;

    public static VaccineDTO from(Vaccine vaccine) {
        if (vaccine == null) return null;
        return VaccineDTO.builder()
                .id(vaccine.getId())
                .name(vaccine.getVaccineName())
                .time(vaccine.getVaccinationTime())
                .build();
    }
}

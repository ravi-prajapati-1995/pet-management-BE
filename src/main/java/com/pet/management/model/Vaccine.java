package com.pet.management.model;

import com.pet.management.dto.UpdateVaccineDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "vaccine")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Vaccine {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "vaccine_name")
    private String vaccineName;

    @Column(name = "vaccination_time")
    private LocalDateTime vaccinationTime;

    @ManyToOne
    private Pet pet;

    public Vaccine(final String vaccineName, final LocalDateTime vaccinationTime, final Pet pet) {
        this.vaccineName = vaccineName;
        this.vaccinationTime = vaccinationTime;
        this.pet = pet;
    }

    public static Vaccine from(final UpdateVaccineDTO dto, final Pet pet) {
        return new Vaccine(dto.getName(), dto.getVaccineTime(), pet);
    }
}
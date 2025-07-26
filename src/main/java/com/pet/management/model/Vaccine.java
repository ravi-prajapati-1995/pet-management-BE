package com.pet.management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "vaccine")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Vaccine {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "vaccine_name")
    private String vaccineName;

    @Column(name = "vaccination_time")
    private LocalDateTime vaccinationTime;
    @ManyToOne
    private Pet pet;
}
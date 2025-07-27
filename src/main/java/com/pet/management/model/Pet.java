package com.pet.management.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Entity
@Table(name = "pet")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@With
public class Pet {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int age;
    @ManyToOne
    private Owner owner;

    @OneToMany(mappedBy="pet")
    private List<Vaccine> vaccines;

    public Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

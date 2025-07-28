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
@EqualsAndHashCode
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

    public Pet(final String name, final int age, final Owner owner, final List<Vaccine> vaccines) {
        this.name = name;
        this.age = age;
        this.owner = owner;
        this.vaccines = vaccines;
    }
}

package com.pet.management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "owner")
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Owner {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToMany(mappedBy="owner")
    private List<Pet> pets;
}
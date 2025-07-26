package com.pet.management.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "owner")
@Getter
@Builder
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
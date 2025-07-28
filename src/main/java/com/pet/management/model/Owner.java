package com.pet.management.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "owner")
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Owner {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToMany(mappedBy="owner")
    private List<Pet> pets;
}
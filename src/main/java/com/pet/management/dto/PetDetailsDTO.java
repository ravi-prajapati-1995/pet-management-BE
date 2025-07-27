package com.pet.management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class PetDetailsDTO {
    private Long id;
    private String name;
    private int age;
    private Long ownerId;
    private String owner;
    private String phoneNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lastVaccine;


}

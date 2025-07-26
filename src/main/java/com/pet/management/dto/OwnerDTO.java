package com.pet.management.dto;

import com.pet.management.model.Owner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class OwnerDTO {
    private int id;
    private String name;
    private String telephone;

    public static OwnerDTO from(Owner owner) {
        if (owner == null) return null;
        return OwnerDTO.builder()
                .id(owner.getId())
                .name(owner.getName())
                .telephone(owner.getPhoneNumber())
                .build();
    }
}

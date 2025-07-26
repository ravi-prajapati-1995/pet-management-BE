package com.pet.management.dto.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OwnerUpdateDTO {
    private String ownerName;
    private String ownerPhone;
}

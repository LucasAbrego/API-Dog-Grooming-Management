package com.zero.dogGrooming.dto;

import com.zero.dogGrooming.enums.PetFurTypeEnum;
import com.zero.dogGrooming.enums.PetSizeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PetUpdateDto {
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Name must contain only letters, numbers, and spaces")
    @Size(max = 50, message = "Name must be at most 50 characters")
    private String name;

    @PositiveOrZero(message = "Age must be a positive number")
    @Max(value = 99, message = "Age must be less than or equal to 99")
    private Long age;

    @Size(max = 50, message = "Breed must be at most 50 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Breed must contain only letters and spaces")
    private String breed;

    @Enumerated(EnumType.STRING)
    private PetSizeEnum size;

    @Enumerated(EnumType.STRING)
    private PetFurTypeEnum furType;

    @Size(max = 100, message = "Considerations must be at most 100 characters")
    private String considerations;

    @Size(min = 7, max = 10, message = "DNI must be between 7 and 10 characters")
    private String ownerDni;
}

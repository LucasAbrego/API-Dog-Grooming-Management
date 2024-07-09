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
public class PetSaveDto {
    @NotBlank(message = "Please add the name")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Name must contain only letters, numbers, and spaces")
    @Size(max = 50, message = "Name must be at most 50 characters")
    private String name;

    @PositiveOrZero(message = "Age must be a positive number")
    @Max(value = 99, message = "Age must be less than or equal to 99")
    private Long age;

    @NotBlank(message = "Please add the breed")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Breed must contain only letters and spaces")
    @Size(max = 50, message = "Breed must be at most 50 characters")
    private String breed;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Please add the size")
    private PetSizeEnum size;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Please add the furType")
    private PetFurTypeEnum furType;

    @Size(max = 100, message = "Considerations must be at most 100 characters")
    private String considerations;

    @NotBlank(message = "Please add the owner DNI")
    @Size(min = 7, max = 10, message = "DNI must be between 7 and 10 characters")
    private String ownerDni;
}

package com.zero.dogGrooming.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientSaveDto {
    @NotBlank(message = "Please add the DNI")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "DNI must contain only letters and numbers")
    @Size(min = 7, max = 10, message = "DNI must be between 7 and 10 characters")
    private String dni;

    @NotBlank(message = "Please add the name")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only letters and spaces")
    @Size(max = 50, message = "Name must be at most 50 characters")
    private String name;

    @NotBlank(message = "Please add the last name")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only letters and spaces")
    @Size(max = 50, message = "Last name must be at most 50 characters")
    private String lastname;

    @NotBlank(message = "Please add the address")
    @Size(max = 100, message = "Address must be at most 100 characters")
    private String address;

    @NotBlank(message = "Please add the phone number")
    @Size(max = 15, message = "Phone number must be at most 15 characters")
    private String phoneNumber;
}

package com.zero.dogGrooming.dto;

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
public class ClientUpdateDto {
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only letters and spaces")
    @Size(max = 50, message = "Name must be at most 50 characters")
    private String name;

    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only letters and spaces")
    @Size(max = 50, message = "Last name must be at most 50 characters")
    private String lastname;

    @Size(max = 100, message = "Address must be at most 100 characters")
    private String address;

    @Size(max = 15, message = "Phone number must be at most 15 characters")
    private String phoneNumber;
}

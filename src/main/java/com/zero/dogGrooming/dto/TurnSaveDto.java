package com.zero.dogGrooming.dto;

import com.zero.dogGrooming.enums.TurnServiceTypeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TurnSaveDto {
    @NotNull(message = "Please add the date and time")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateAndTime;
    //@Future(message = "Date and time must be in the future") //Para facilitar las pruebas esta deshabilitado

    @NotNull(message = "Please add the price")
    @PositiveOrZero(message = "Price must be a positive number or zero")
    private Double price;

    @NotNull(message = "Please add the pet ID")
    @Positive(message = "Pet ID must be a positive number or zero")
    private Long petId;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Please add the service type")
    private TurnServiceTypeEnum serviceType;

}

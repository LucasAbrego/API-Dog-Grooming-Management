package com.zero.dogGrooming.dto;

import com.zero.dogGrooming.enums.TurnServiceTypeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
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
public class TurnUpdateDto {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateAndTime;

    @DecimalMin(value = "0.0", message = "Price must be a positive number or zero")
    private Double price;

    @PositiveOrZero(message = "Pet ID must be a positive number or zero")
    private Long petId;

    @Enumerated(EnumType.STRING)
    private TurnServiceTypeEnum serviceType;

}

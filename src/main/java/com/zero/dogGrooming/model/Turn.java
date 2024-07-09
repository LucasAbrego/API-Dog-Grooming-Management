package com.zero.dogGrooming.model;

import com.zero.dogGrooming.dto.TurnSaveDto;
import com.zero.dogGrooming.dto.TurnUpdateDto;
import com.zero.dogGrooming.enums.TurnServiceTypeEnum;
import com.zero.dogGrooming.enums.TurnStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Turn {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime dateAndTime;
    private Double price;
    @Enumerated(EnumType.STRING)
    private TurnStatusEnum status;
    @Enumerated(EnumType.STRING)
    private TurnServiceTypeEnum serviceType;
    private Boolean paid;

    @ManyToOne
    @JoinColumn(name = "pet")
    private Pet pet;

    public Turn(TurnSaveDto turnSaveDto, Pet pet) {
        this.setDateAndTime(turnSaveDto.getDateAndTime());
        this.setPrice(turnSaveDto.getPrice());
        this.setServiceType(turnSaveDto.getServiceType());
        this.status = TurnStatusEnum.PENDING;
        this.setPet(pet);
        this.paid = false;
    }


    public void updateFromDto(TurnUpdateDto turnUpdateDto, Pet pet) {
        if (turnUpdateDto.getDateAndTime() != null) {
            this.setDateAndTime(turnUpdateDto.getDateAndTime());
        }
        if (turnUpdateDto.getPrice() != null) {
            this.setPrice(turnUpdateDto.getPrice());
        }
        if (turnUpdateDto.getServiceType() != null) {
            this.setServiceType(turnUpdateDto.getServiceType());
        }
        if (pet != null) {
            this.setPet(pet);
        }
    }
}

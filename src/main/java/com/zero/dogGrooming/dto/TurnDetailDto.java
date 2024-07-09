package com.zero.dogGrooming.dto;

import com.zero.dogGrooming.enums.TurnServiceTypeEnum;
import com.zero.dogGrooming.enums.TurnStatusEnum;
import com.zero.dogGrooming.model.Pet;
import com.zero.dogGrooming.model.Turn;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TurnDetailDto {
    private Long id;
    private LocalDateTime dateTime;
    private String petName;
    private double price;
    @Enumerated(EnumType.STRING)
    private TurnStatusEnum status;
    @Enumerated(EnumType.STRING)
    private TurnServiceTypeEnum serviceType;


    public static TurnDetailDto fromTurn(Turn turn) {
        TurnDetailDto turnDetailDTO = new TurnDetailDto();
        turnDetailDTO.setId(turn.getId());
        if (turn.getDateAndTime() != null) {
            turnDetailDTO.setDateTime(turn.getDateAndTime());
        }
        if (turn.getPrice() != null) {
            turnDetailDTO.setPrice(turn.getPrice());
        }
        Pet pet = turn.getPet();
        if (pet != null) {
            if (pet.getName() != null) {
                turnDetailDTO.setPetName(pet.getName());
            }
        }
        if (turn.getStatus() != null) {
            turnDetailDTO.setStatus(turn.getStatus());
        }
        if (turn.getServiceType() != null) {
            turnDetailDTO.setServiceType(turn.getServiceType());
        }
        return turnDetailDTO;
    }

    public static List<TurnDetailDto> fromTurns(List<Turn> turns) {
        List<TurnDetailDto> turnDetailDTOs = new ArrayList<>();
        for (Turn turn : turns) {
            TurnDetailDto turnDetailDTO = fromTurn(turn);
            turnDetailDTOs.add(turnDetailDTO);
        }
        return turnDetailDTOs;
    }
}

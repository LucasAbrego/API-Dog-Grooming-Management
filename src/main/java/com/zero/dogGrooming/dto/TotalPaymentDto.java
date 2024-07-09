package com.zero.dogGrooming.dto;

import com.zero.dogGrooming.model.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TotalPaymentDto {
    private String fullName;
    private String dni;
    private double totalAmount;
    private List<TurnDetailDto> turnDetails;

    public TotalPaymentDto(double total,
                           List<TurnDetailDto> listTurnDetail,
                           Client client) {
        this.setTotalAmount(total);
        this.setTurnDetails(listTurnDetail);
        this.setFullName(client.getFullName());
        this.setDni(client.getDni());
    }
}
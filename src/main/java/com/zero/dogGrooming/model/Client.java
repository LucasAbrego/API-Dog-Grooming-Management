package com.zero.dogGrooming.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zero.dogGrooming.dto.ClientSaveDto;
import com.zero.dogGrooming.dto.ClientUpdateDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {
    @Id
    private String dni;
    private String name;
    private String lastname;
    private String address;
    private String phoneNumber;
    private Boolean active;

    public Client(ClientSaveDto clientSaveDto) {
        this.setDni(clientSaveDto.getDni());
        this.setName(clientSaveDto.getName());
        this.setLastname(clientSaveDto.getLastname());
        this.setAddress(clientSaveDto.getAddress());
        this.setPhoneNumber(clientSaveDto.getPhoneNumber());
        this.setActive(true);
    }

    @JsonIgnore
    public String getFullName() {
        return this.getLastname() + " " + this.getName();
    }

    public void update(ClientUpdateDto clientUpdateDto) {
        if (clientUpdateDto.getName() != null) {
            this.setName(clientUpdateDto.getName());
        }
        if (clientUpdateDto.getLastname() != null) {
            this.setLastname(clientUpdateDto.getLastname());
        }
        if (clientUpdateDto.getAddress() != null) {
            this.setAddress(clientUpdateDto.getAddress());
        }
        if (clientUpdateDto.getPhoneNumber() != null) {
            this.setPhoneNumber(clientUpdateDto.getPhoneNumber());
        }
    }
}

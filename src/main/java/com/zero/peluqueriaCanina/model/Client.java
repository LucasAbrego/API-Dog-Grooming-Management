package com.zero.peluqueriaCanina.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zero.peluqueriaCanina.dto.ClientSaveDto;
import com.zero.peluqueriaCanina.dto.ClientUpdateDto;
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
    private String lastName;
    private String address;
    private String phoneNumber;
    private Boolean active;

    public Client(ClientSaveDto clientSaveDto) {
        this.setDni(clientSaveDto.getDni());
        this.setName(clientSaveDto.getName());
        this.setLastName(clientSaveDto.getLastName());
        this.setAddress(clientSaveDto.getAddress());
        this.setPhoneNumber(clientSaveDto.getPhoneNumber());
        this.setActive(true);
    }

    @JsonIgnore
    public String getFullName() {
        return this.getLastName() + " " + this.getName();
    }

    public void update(ClientUpdateDto clientUpdateDto) {
        if (clientUpdateDto.getName() != null) {
            this.setName(clientUpdateDto.getName());
        }
        if (clientUpdateDto.getLastName() != null) {
            this.setLastName(clientUpdateDto.getLastName());
        }
        if (clientUpdateDto.getAddress() != null) {
            this.setAddress(clientUpdateDto.getAddress());
        }
        if (clientUpdateDto.getPhoneNumber() != null) {
            this.setPhoneNumber(clientUpdateDto.getPhoneNumber());
        }
    }
}

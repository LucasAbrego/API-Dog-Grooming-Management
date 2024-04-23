package com.zero.peluqueriaCanina.service;

import com.zero.peluqueriaCanina.dto.ClientSaveDto;
import com.zero.peluqueriaCanina.dto.ClientUpdateDto;
import com.zero.peluqueriaCanina.dto.TotalPaymentDto;
import com.zero.peluqueriaCanina.model.Client;

import java.util.List;

public interface IClientService {
    Client saveClient(ClientSaveDto clientSaveDto);

    List<Client> findAllClients();

    List<Client> findActiveClients();

    Client findClientByDni(String dni);

    Client updateClient(String dni, ClientUpdateDto newDataClient);

    void deleteAll();

    String deleteClient(String dni);

    String deactivateClient(String dni);

    String activateClient(String dni);

    TotalPaymentDto getClientBilling(String dniClient);
}

package com.zero.dogGrooming.service;

import com.zero.dogGrooming.dto.ClientSaveDto;
import com.zero.dogGrooming.dto.ClientUpdateDto;
import com.zero.dogGrooming.dto.TotalPaymentDto;
import com.zero.dogGrooming.dto.TurnDetailDto;
import com.zero.dogGrooming.exception.client.*;
import com.zero.dogGrooming.exception.turn.TurnNotFoundException;
import com.zero.dogGrooming.model.Client;
import com.zero.dogGrooming.model.Turn;
import com.zero.dogGrooming.repository.ClientRepository;
import com.zero.dogGrooming.repository.PetRepository;
import com.zero.dogGrooming.repository.TurnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService implements IClientService {

    @Autowired
    private ClientRepository repoClient;
    @Autowired
    private PetRepository repoPet;
    @Autowired
    private TurnRepository repoTurn;

    @Override
    public Client saveClient(ClientSaveDto clientSaveDto) {
        if (!repoClient.existsByDni(clientSaveDto.getDni())) {
            return repoClient.save(new Client(clientSaveDto));
        } else {
            throw new ClientSaveException("Client with DNI " + clientSaveDto.getDni() + " already exists");
        }
    }


    @Override
    public List<Client> findActiveClients() {
        List<Client> activeClients = repoClient.findByActiveTrue();
        if (activeClients.isEmpty()) {
            throw new ClientNotFoundException("No active clients found.");
        }
        return activeClients;
    }

    @Override
    public Client findClientByDni(String dni) {
        return repoClient.findById(dni)
                .orElseThrow(() -> new ClientNotFoundException("Client with DNI " + dni + " not found."));
    }

    @Override
    public List<Client> findAllClients() {
        List<Client> clients = repoClient.findAll();
        if (clients.isEmpty()) {
            throw new ClientNotFoundException("No clients found.");
        }
        return clients;
    }

    @Override
    public Client updateClient(String dni, ClientUpdateDto clientUpdateDto) {
        Client client = this.findClientByDni(dni);
        client.update(clientUpdateDto);
        return repoClient.save(client);
    }

    @Override
    public void deleteAll() {
        try {
            repoClient.deleteAll();
        } catch (Exception e) {
            throw new ClientDeleteException("Error attempting to delete all clients.");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String deleteClient(String dni) {
        Client client = this.findClientByDni(dni);
        try {
            repoTurn.deleteByClient(client);
            repoPet.deleteByOwner(client);
            repoClient.deleteById(dni);
            return "The client with DNI " + dni + " has been successfully deleted.";
        } catch (Exception e) {
            throw new ClientDeleteException();
        }
    }

    @Override
    public String deactivateClient(String dni) {
        Client client = this.findClientByDni(dni);
        if (client.getActive()) {
            client.setActive(false);
            repoClient.save(client);
            return "The client with DNI " + dni + " has been deactivated.";
        } else {
            throw new ClientAlreadyInactiveException(dni);
        }
    }

    @Override
    public TotalPaymentDto getClientBilling(String dni) {
        Client client = this.findClientByDni(dni);
        List<TurnDetailDto> listTurnDetail = new ArrayList<>();
        double total = 0;
        List<Turn> listTurn = repoTurn.findUnpaidTurnsByOwnerDni(dni);
        System.out.println(listTurn);
        if (listTurn != null) {
            for (Turn turn : listTurn) {
                listTurnDetail.add(TurnDetailDto.fromTurn(turn));
                total = total + turn.getPrice();
            }
        }
        if (!(listTurnDetail.isEmpty())) {
            return new TotalPaymentDto(total, listTurnDetail, client);
        } else {
            throw new TurnNotFoundException("No unpaid turns found associated with the client with DNI " + dni);
        }
    }

    @Override
    public String activateClient(String dni) {
        Client client = this.findClientByDni(dni);
        if (!client.getActive()) {
            client.setActive(true);
            repoClient.save(client);
            return "The client with DNI " + dni + " has been activated.";
        } else {
            throw new ClientAlreadyActiveException(dni);
        }
    }
}
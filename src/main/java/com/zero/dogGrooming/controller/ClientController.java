package com.zero.dogGrooming.controller;

import com.zero.dogGrooming.dto.ClientSaveDto;
import com.zero.dogGrooming.dto.ClientUpdateDto;
import com.zero.dogGrooming.dto.TotalPaymentDto;
import com.zero.dogGrooming.model.Client;
import com.zero.dogGrooming.service.IClientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private IClientService servClient;

    @PostMapping("/save")
    public ResponseEntity<Client> saveClient(@Valid @RequestBody ClientSaveDto clientSaveDto) {
        Client savedClient = servClient.saveClient(clientSaveDto);
        return ResponseEntity.ok(savedClient);
    }

    @GetMapping("")
    public ResponseEntity<List<Client>> findActiveClients() {
        List<Client> listClients = servClient.findActiveClients();
        return ResponseEntity.ok(listClients);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Client>> findAllClients() {
        List<Client> listClients = servClient.findAllClients();
        return ResponseEntity.ok(listClients);
    }

    @GetMapping("/{dni}")
    public ResponseEntity<Client> findClientByDni(@PathVariable
                                                  @Pattern(regexp = "^[a-zA-Z0-9]{7,10}$", message = "DNI must be between 7 and 10 characters and contain only letters and numbers")
                                                  String dni) {
        return ResponseEntity.ok(servClient.findClientByDni(dni));
    }

    @GetMapping("/{dni}/billing")
    public ResponseEntity<TotalPaymentDto> getClientBilling(@PathVariable
                                                                @Pattern(regexp = "^[a-zA-Z0-9]{7,10}$", message = "DNI must be between 7 and 10 characters and contain only letters and numbers")
                                                                String dni) {
        return ResponseEntity.ok(servClient.getClientBilling(dni));
    }

    @PutMapping("/edit/{dni}")
    public ResponseEntity<Client> updateClient(@PathVariable
                                                   @Pattern(regexp = "^[a-zA-Z0-9]{7,10}$", message = "DNI must be between 7 and 10 characters and contain only letters and numbers")
                                                   String dni,
                                               @Valid @RequestBody ClientUpdateDto newDataClient) {
        Client updatedClient = servClient.updateClient(dni, newDataClient);
        return ResponseEntity.ok(updatedClient);
    }

    @PutMapping("/deactivate/{dni}")
    public ResponseEntity<String> deactivateClient(@PathVariable
                                                       @Pattern(regexp = "^[a-zA-Z0-9]{7,10}$", message = "DNI must be between 7 and 10 characters and contain only letters and numbers")
                                                       String dni) {
        return ResponseEntity.ok(servClient.deactivateClient(dni));
    }

    @PutMapping("/activate/{dni}")
    public ResponseEntity<String> activateClient(@PathVariable
                                                     @Pattern(regexp = "^[a-zA-Z0-9]{7,10}$", message = "DNI must be between 7 and 10 characters and contain only letters and numbers")
                                                     String dni) {
        return ResponseEntity.ok(servClient.activateClient(dni));
    }

    @DeleteMapping("/delete/{dni}")
    public ResponseEntity<String> deleteClient(@PathVariable
                                                   @Pattern(regexp = "^[a-zA-Z0-9]{7,10}$", message = "DNI must be between 7 and 10 characters and contain only letters and numbers")
                                                   String dni) {
        return ResponseEntity.ok(servClient.deleteClient(dni));
    }
}

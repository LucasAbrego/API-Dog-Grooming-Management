package com.zero.dogGrooming.controller;

import com.zero.dogGrooming.dto.PetSaveDto;
import com.zero.dogGrooming.dto.PetUpdateDto;
import com.zero.dogGrooming.model.Pet;
import com.zero.dogGrooming.service.IPetService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {
    @Autowired
    private IPetService servPet;

    @PostMapping("/save")
    public ResponseEntity<Pet> savePet(@Valid @RequestBody PetSaveDto petDto) {
        return ResponseEntity.ok(servPet.savePet(petDto));
    }

    @GetMapping("")
    public ResponseEntity<List<Pet>> findActivePets() {
        return ResponseEntity.ok(servPet.findActivePets());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pet>> findAllPets() {
        return ResponseEntity.ok(servPet.findAllPets());
    }

    @GetMapping("/client/{dni}")
    public ResponseEntity<List<Pet>> findPetsByClient(@PathVariable
                                                      @Pattern(regexp = "^[a-zA-Z0-9]{7,10}$", message = "DNI must be between 7 and 10 characters and contain only letters and numbers")
                                                      String dni) {
        return ResponseEntity.ok(servPet.findPetsByClient(dni));
    }

    @GetMapping("/{petId}")
    public ResponseEntity<Pet> findPetById(@PathVariable
                                           @Positive(message = "Pet ID must be a positive number")
                                           Long petId) {
        return ResponseEntity.ok(servPet.findPetById(petId));
    }

    @PutMapping("/edit/{petId}")
    public ResponseEntity<Pet> updatePet(@PathVariable
                                             @Positive(message = "Pet ID must be a positive number")
                                             Long petId,
                                         @Valid @RequestBody PetUpdateDto petUpdateDto) {
        return ResponseEntity.ok(servPet.updatePet(petId, petUpdateDto));
    }

    @PutMapping("/deactivate/{petId}")
    public ResponseEntity<String> deactivatePet(@PathVariable
                                                    @Positive(message = "Pet ID must be a positive number")
                                                    Long petId) {
        return ResponseEntity.ok(servPet.deactivatePet(petId));
    }

    @PutMapping("/activate/{petId}")
    public ResponseEntity<String> activatePet(@PathVariable
                                                  @Positive(message = "Pet ID must be a positive number")
                                                  Long petId) {
        return ResponseEntity.ok(servPet.activatePet(petId));
    }

    @DeleteMapping("/delete/{petId}")
    public ResponseEntity<String> deletePet(@PathVariable
                                                @Positive(message = "Pet ID must be a positive number")
                                                Long petId) {
        return ResponseEntity.ok(servPet.deletePet(petId));
    }
}

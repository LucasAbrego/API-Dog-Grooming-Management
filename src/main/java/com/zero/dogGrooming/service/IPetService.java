package com.zero.dogGrooming.service;

import com.zero.dogGrooming.dto.PetSaveDto;
import com.zero.dogGrooming.dto.PetUpdateDto;
import com.zero.dogGrooming.model.Pet;

import java.util.List;

public interface IPetService {
    Pet savePet(PetSaveDto petDto);

    List<Pet> findAllPets();

    Pet findPetById(Long id);

    Pet updatePet(Long id, PetUpdateDto PetUpdateDto);

    String deletePet(Long id);

    String deactivatePet(Long petId);

    String activatePet(Long petId);

    List<Pet> findActivePets();

    List<Pet> findPetsByClient(String dni);

    void deleteAll();
}

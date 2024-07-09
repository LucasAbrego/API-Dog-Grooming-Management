package com.zero.dogGrooming.service;

import com.zero.dogGrooming.dto.PetSaveDto;
import com.zero.dogGrooming.dto.PetUpdateDto;
import com.zero.dogGrooming.exception.pet.*;
import com.zero.dogGrooming.model.Client;
import com.zero.dogGrooming.model.Pet;
import com.zero.dogGrooming.repository.PetRepository;
import com.zero.dogGrooming.repository.TurnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PetService implements IPetService {
    @Autowired
    private PetRepository repoPet;
    @Autowired
    private IClientService servClient;
    @Autowired
    private TurnRepository repoTurn;

    @Override
    public Pet savePet(PetSaveDto petDto) {
        Pet pet = new Pet(petDto);
        Client client = servClient.findClientByDni(petDto.getOwnerDni());
        pet.setOwner(client);
        try {
            return repoPet.save(pet);
        } catch (Exception e) {
            throw new PetSaveException();
        }
    }

    @Override
    public List<Pet> findActivePets() {
        List<Pet> listPets = repoPet.findByActiveTrue();
        if (!listPets.isEmpty()) {
            return listPets;
        } else {
            throw new PetNotFoundException("No active pets found");
        }
    }

    @Override
    public List<Pet> findPetsByClient(String dni) {
        Client client = servClient.findClientByDni(dni);
        List<Pet> listPets = repoPet.findByOwner(client);
        if (!listPets.isEmpty()) {
            return listPets;
        } else {
            throw new PetNotFoundException("No pets associated with the client found");
        }
    }

    @Override
    public void deleteAll() {
        try {
            repoPet.deleteAll();
        } catch (Exception e) {
            throw new PetDeleteException("Error trying to delete all pets");
        }
    }

    @Override
    public List<Pet> findAllPets() {
        List<Pet> listPets = repoPet.findAll();
        if (!listPets.isEmpty()) {
            return listPets;
        } else {
            throw new PetNotFoundException("No registered pets found");
        }
    }

    @Override
    public Pet findPetById(Long id) {
        return repoPet.findById(id)
                .orElseThrow(() -> new PetNotFoundException("The pet with ID " + id + " was not found."));
    }

    @Override
    public Pet updatePet(Long id, PetUpdateDto newPetDto) {
        try {
            Pet pet = this.findPetById(id);
            pet.fromPetUpdateDto(newPetDto);
            if (newPetDto.getOwnerDni() != null) {
                Client client = servClient.findClientByDni(newPetDto.getOwnerDni());
                pet.setOwner(client);
            }
            return repoPet.save(pet);
        } catch (Exception e) {
            throw new PetSaveException();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String deletePet(Long id) {
        Pet pet = this.findPetById(id);
        try {
            repoTurn.deleteByPet(pet);
            repoPet.delete(pet);
            return "Pet has been successfully deleted";
        } catch (Exception e) {
            throw new PetDeleteException("An error occurred while deleting the pet");
        }
    }


    @Override
    public String deactivatePet(Long petId) {
        Pet pet = this.findPetById(petId);
        if (pet.getActive()) {
            pet.setActive(false);
            repoPet.save(pet);
            return "The pet with ID " + petId + " has been deactivated";
        } else {
            throw new PetAlreadyInactiveException(petId);
        }
    }

    @Override
    public String activatePet(Long petId) {
        Pet pet = this.findPetById(petId);
        if (!pet.getActive()) {
            pet.setActive(true);
            repoPet.save(pet);
            return "The pet with ID " + petId + " has been activated";
        } else {
            throw new PetAlreadyActiveException(petId);
        }
    }
}

package com.zero.dogGrooming.service;

import com.zero.dogGrooming.dto.TurnDetailDto;
import com.zero.dogGrooming.dto.TurnSaveDto;
import com.zero.dogGrooming.dto.TurnUpdateDto;
import com.zero.dogGrooming.enums.TurnStatusEnum;
import com.zero.dogGrooming.exception.turn.*;
import com.zero.dogGrooming.model.Pet;
import com.zero.dogGrooming.model.Turn;
import com.zero.dogGrooming.repository.TurnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class TurnService implements ITurnService {
    @Autowired
    private TurnRepository repoTurn;
    @Autowired
    private IPetService servPet;

    @Override
    public Turn saveTurn(TurnSaveDto turnSaveDto) {
        Pet pet = servPet.findPetById(turnSaveDto.getPetId());
        try {
            Turn turn = new Turn(turnSaveDto, pet);
            return repoTurn.save(turn);
        } catch (Exception e) {
            throw new TurnSaveException();
        }
    }

    @Override
    public List<Turn> findAllTurns() {
        List<Turn> listTurns = repoTurn.findAll();
        if (!listTurns.isEmpty()) {
            return listTurns;
        } else {
            throw new TurnNotFoundException("No turns were found");
        }
    }

    @Override
    public Turn findTurnById(Long id) {
        return repoTurn.findById(id).
                orElseThrow(() -> new TurnNotFoundException("No turn found with ID " + id));
    }

    @Override
    public Turn updateTurn(Long id, TurnUpdateDto turnUpdateDto) {
        Pet pet = null;
        Turn turn = this.findTurnById(id);
        if (turnUpdateDto.getPetId() != null) {
            pet = servPet.findPetById(turnUpdateDto.getPetId());
        }
        turn.updateFromDto(turnUpdateDto, pet);
        return repoTurn.save(turn);
    }


    public String deleteTurn(Long id) {
        try {
            this.findTurnById(id);
            repoTurn.deleteById(id);
            return "The turn with ID " + id + " has been successfully deleted.";
        } catch (Exception e) {
            throw new TurnDeleteException();
        }
    }


    @Override
    public String cancelTurn(Long id) {
        Turn turn = this.findTurnById(id);
        if (turn.getStatus() == TurnStatusEnum.COMPLETED) {
            throw new TurnStatusConflictException("The turn has already been completed and cannot be canceled.");
        } else if (turn.getStatus() == TurnStatusEnum.CANCELED) {
            throw new TurnStatusConflictException("The turn with ID " + id + " has already been canceled.");
        } else if (turn.getDateAndTime().isBefore(LocalDateTime.now())) {
            throw new TurnStatusConflictException("The turn cannot be canceled because it has already passed.");
        } else {
            turn.setStatus(TurnStatusEnum.CANCELED);
            repoTurn.save(turn);
            return "Turn number " + id + " has been canceled.";
        }
    }


    @Override
    public void deleteAll() {
        try {
            repoTurn.deleteAll();
        } catch (Exception e) {
            throw new TurnDeleteException("Error while attempting to delete all turns");
        }
    }

    @Override
    public List<Turn> findTurnsByPet(Long id) {
        List<Turn> listTurns = repoTurn.findTurnsByPetId(id);
        if (!listTurns.isEmpty()) {
            return listTurns;
        } else {
            throw new TurnNotFoundException("No turns associated with pet ID " + id + " were found");
        }
    }

    @Override
    public List<TurnDetailDto> findTurnsByClient(String dni) {
        List<Turn> listTurns = repoTurn.findTurnsByClientDni(dni);
        if (!listTurns.isEmpty()) {
            return TurnDetailDto.fromTurns(listTurns);
        } else {
            throw new TurnNotFoundException("Could not find turns associated with the client with ID " + dni);
        }
    }

    @Override
    public List<Turn> getTurnsInDateRange(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);
        List<Turn> listTurns = repoTurn.findByDateAndTimeBetween(startDateTime, endDateTime);
        if (!listTurns.isEmpty()) {
            return listTurns;
        } else {
            throw new TurnNotFoundException("No turns were found within the provided time range.");
        }
    }

    @Override
    public String payTurn(Long id) {
        try {
            Turn turn = this.findTurnById(id);
            if (!turn.getPaid()) {
                if (turn.getStatus() == TurnStatusEnum.COMPLETED) {
                    turn.setPaid(true);
                    turn.setStatus(TurnStatusEnum.PAID);
                    repoTurn.save(turn);
                    return "Payment was successful";
                } else {
                    throw new TurnStatusConflictException("You can only pay for a turn if it has already been completed");
                }
            } else {
                throw new TurnStatusConflictException("The turn you are trying to pay for has already been paid for");
            }
        } catch (Exception e) {
            throw new TurnPaymentException("An error occurred with the payment.");
        }
    }
}


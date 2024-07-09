package com.zero.dogGrooming.service;

import com.zero.dogGrooming.dto.TurnDetailDto;
import com.zero.dogGrooming.dto.TurnSaveDto;
import com.zero.dogGrooming.dto.TurnUpdateDto;
import com.zero.dogGrooming.model.Turn;

import java.time.LocalDate;
import java.util.List;

public interface ITurnService {
    Turn saveTurn(TurnSaveDto turnDto);

    List<Turn> findAllTurns();

    Turn findTurnById(Long id);

    Turn updateTurn(Long id, TurnUpdateDto turnUpdateDto);

    String deleteTurn(Long id);

    String cancelTurn(Long id);

    void deleteAll();

    List<Turn> findTurnsByPet(Long id);

    List<TurnDetailDto> findTurnsByClient(String dni);

    List<Turn> getTurnsInDateRange(LocalDate startDate, LocalDate endDate);

    String payTurn(Long id);
}

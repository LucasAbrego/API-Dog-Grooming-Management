package com.zero.dogGrooming.controller;

import com.zero.dogGrooming.dto.TurnDetailDto;
import com.zero.dogGrooming.dto.TurnSaveDto;
import com.zero.dogGrooming.dto.TurnUpdateDto;
import com.zero.dogGrooming.model.Turn;
import com.zero.dogGrooming.service.ITurnService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/turns")
@Validated
public class TurnController {

    @Autowired
    private ITurnService servTurn;

    @PostMapping("/save")
    public ResponseEntity<Turn> saveTurn(@Valid @RequestBody TurnSaveDto turnDto) {
        return ResponseEntity.ok(servTurn.saveTurn(turnDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Turn>> findAllTurns() {
        return ResponseEntity.ok(servTurn.findAllTurns());
    }

    @GetMapping("/{turnId}")
    public ResponseEntity<Turn> findTurnById(@PathVariable
                                             @Positive(message = "Turn ID must be a positive number")
                                             Long turnId) {
        return ResponseEntity.ok(servTurn.findTurnById(turnId));
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<Turn>> findTurnsByPetId(@PathVariable
                                                       @Positive(message = "Pet ID must be a positive number")
                                                       Long petId) {
        return ResponseEntity.ok(servTurn.findTurnsByPet(petId));
    }

    @GetMapping("/client/{dni}")
    public ResponseEntity<List<TurnDetailDto>> findTurnsByClient(@PathVariable
                                                                 @Pattern(regexp = "^[a-zA-Z0-9]{7,10}$", message = "DNI must be between 7 and 10 characters and contain only letters and numbers")
                                                                 String dni) {
        return ResponseEntity.ok(servTurn.findTurnsByClient(dni));
    }

    @GetMapping("/findByDataRange")
    public ResponseEntity<List<Turn>> getTurnsInDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(servTurn.getTurnsInDateRange(startDate, endDate));
    }

    @PutMapping("/edit/{turnId}")
    public ResponseEntity<Turn> updateTurn(@PathVariable
                                           @Positive(message = "Turn ID must be a positive number")
                                           Long turnId,
                                           @Valid @RequestBody TurnUpdateDto turnUpdateDto) {
        return ResponseEntity.ok(servTurn.updateTurn(turnId, turnUpdateDto));
    }

    @PutMapping("/cancel/{turnId}")
    public ResponseEntity<String> cancelTurn(@PathVariable
                                             @Positive(message = "Turn ID must be a positive number")
                                             Long turnId) {
        return ResponseEntity.ok(servTurn.cancelTurn(turnId));
    }

    @PutMapping("/pay/{turnId}")
    public ResponseEntity<String> payTurn(@PathVariable
                                          @Positive(message = "Turn ID must be a positive number")
                                          Long turnId) {
        return ResponseEntity.ok(servTurn.payTurn(turnId));
    }

    @DeleteMapping("/delete/{turnId}")
    public ResponseEntity<String> deleteTurn(@PathVariable
                                             @Positive(message = "Turn ID must be a positive number")
                                             Long turnId) {
        return ResponseEntity.ok(servTurn.deleteTurn(turnId));
    }
}


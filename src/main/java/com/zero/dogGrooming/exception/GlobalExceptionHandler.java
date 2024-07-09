package com.zero.dogGrooming.exception;

import com.zero.dogGrooming.exception.client.*;
import com.zero.dogGrooming.exception.pet.*;
import com.zero.dogGrooming.exception.turn.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    //CLIENT
    @ExceptionHandler(ClientAlreadyActiveException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleClientAlreadyActiveException(ClientAlreadyActiveException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ClientAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleClientAlreadyExistsException(ClientAlreadyExistsException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ClientAlreadyInactiveException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleClientAlreadyInactiveException(ClientAlreadyInactiveException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ClientDeleteException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleClientDeleteException(ClientDeleteException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleClientNotFoundException(ClientNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ClientSaveException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleClientSaveException(ClientSaveException ex) {
        return ex.getMessage();
    }


    //PET
    @ExceptionHandler(PetAlreadyActiveException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handlePetAlreadyActiveException(PetAlreadyActiveException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(PetAlreadyInactiveException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handlePetAlreadyInactiveException(PetAlreadyInactiveException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(PetDeleteException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handlePetDeleteException(PetDeleteException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(PetNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlePetNotFoundException(PetNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(PetSaveException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handlePetSaveException(PetSaveException ex) {
        return ex.getMessage();
    }

    //TURN
    @ExceptionHandler(TurnDeleteException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleTurnDeleteException(TurnDeleteException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(TurnNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleTurnNotFoundException(TurnNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(TurnPaymentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleTurnPaymentException(TurnPaymentException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(TurnSaveException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleTurnSaveException(TurnSaveException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(TurnStatusConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleTurnAlreadyCancelledException(TurnStatusConflictException ex) {
        return ex.getMessage();
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->{
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}

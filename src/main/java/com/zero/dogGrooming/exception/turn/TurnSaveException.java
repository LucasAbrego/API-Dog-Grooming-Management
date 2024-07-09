package com.zero.dogGrooming.exception.turn;

public class TurnSaveException extends RuntimeException {
    public TurnSaveException() {
        super("An error occurred while trying to save the turn.");
    }

    public TurnSaveException(String messaje) {
        super(messaje);
    }
}


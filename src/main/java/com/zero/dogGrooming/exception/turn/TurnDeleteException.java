package com.zero.dogGrooming.exception.turn;

public class TurnDeleteException extends RuntimeException {
    public TurnDeleteException(String message) {
        super(message);
    }

    public TurnDeleteException() {
        super("Error while trying to delete the turn");
    }
}


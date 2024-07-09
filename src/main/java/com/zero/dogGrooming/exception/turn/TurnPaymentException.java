package com.zero.dogGrooming.exception.turn;

public class TurnPaymentException extends RuntimeException {
    public TurnPaymentException(String id) {
        super("An error occurred while processing the payment. Turn ID: " + id);
    }
}

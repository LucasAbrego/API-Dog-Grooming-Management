package com.zero.dogGrooming.exception.client;

public class ClientAlreadyActiveException extends RuntimeException {
    public ClientAlreadyActiveException(String dni) {
        super("The client with DNI " + dni + " is already active.");
    }
}

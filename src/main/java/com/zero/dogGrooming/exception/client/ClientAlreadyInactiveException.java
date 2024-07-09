package com.zero.dogGrooming.exception.client;

public class ClientAlreadyInactiveException extends RuntimeException {
    public ClientAlreadyInactiveException(String dni) {
        super("The client with DNI " + dni + " is already deactivated.");
    }
}

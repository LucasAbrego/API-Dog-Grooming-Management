package com.zero.dogGrooming.exception.client;

public class ClientAlreadyExistsException extends RuntimeException {
    public ClientAlreadyExistsException(String dni) {
        super("The client with DNI " + dni + " already exists.");
    }
}

package com.zero.dogGrooming.exception.client;

public class ClientSaveException extends RuntimeException {
    public ClientSaveException() {
        super("Error while trying to save the client.");
    }

    public ClientSaveException(String messaje) {
        super(messaje);
    }
}

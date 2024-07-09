package com.zero.dogGrooming.exception.client;

public class ClientDeleteException extends RuntimeException {
    public ClientDeleteException() {

        super("Error while trying to delete the client.");
    }

    public ClientDeleteException(String messaje) {
        super(messaje);
    }
}

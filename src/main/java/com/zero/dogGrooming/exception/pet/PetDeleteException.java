package com.zero.dogGrooming.exception.pet;

public class PetDeleteException extends RuntimeException {
    public PetDeleteException(String message) {
        super(message);
    }

    public PetDeleteException() {
        super("Error while trying to delete the pet");
    }
}

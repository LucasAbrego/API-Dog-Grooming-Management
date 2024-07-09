package com.zero.dogGrooming.exception.pet;

public class PetSaveException extends RuntimeException {
    public PetSaveException() {
        super("Error while trying to save the pet");
    }

    public PetSaveException(String messaje) {
        super(messaje);
    }
}

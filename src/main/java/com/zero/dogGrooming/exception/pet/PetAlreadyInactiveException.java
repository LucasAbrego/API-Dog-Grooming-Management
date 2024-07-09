package com.zero.dogGrooming.exception.pet;

public class PetAlreadyInactiveException extends RuntimeException {
    public PetAlreadyInactiveException(Long id) {
        super("The pet with ID " + id + " is already deactivated.");
    }
}

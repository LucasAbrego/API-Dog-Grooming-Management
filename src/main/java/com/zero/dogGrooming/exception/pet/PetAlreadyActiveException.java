package com.zero.dogGrooming.exception.pet;

public class PetAlreadyActiveException extends RuntimeException {
    public PetAlreadyActiveException(Long id) {
        super("The pet with ID " + id + " is already active.");
    }
}

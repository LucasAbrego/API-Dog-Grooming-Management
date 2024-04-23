package com.zero.peluqueriaCanina.exception.pet;

public class PetNotFoundException extends RuntimeException {
    public PetNotFoundException(String message) {
        super(message);
    }
}


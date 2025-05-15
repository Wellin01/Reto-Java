package com.tcs.reto.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String mensaje) {
        super(mensaje);
    }
}

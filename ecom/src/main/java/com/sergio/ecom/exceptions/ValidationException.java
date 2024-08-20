package com.sergio.ecom.exceptions;

public class ValidationException extends RuntimeException{

    // método de excepción de validación
    public ValidationException(String message){
        super(message);
    }
}

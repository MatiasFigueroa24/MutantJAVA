package com.ExamenP.Mutant.Excepcion;

public class IncorrectNitrogenBaseException extends Exception {

    private String message;

    public IncorrectNitrogenBaseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

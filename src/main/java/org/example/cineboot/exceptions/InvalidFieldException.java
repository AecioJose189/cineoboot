package org.example.cineboot.exceptions;

public class InvalidFieldException extends RuntimeException {
    private String field;

    public InvalidFieldException(String message, String field) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}

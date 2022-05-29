package com.finalProject.Project.exception;

public class InvalinInput extends RuntimeException{
    public InvalinInput(String message) {
        super(message);
    }

    public InvalinInput(String message, Throwable cause) {
        super(message, cause);
    }
}

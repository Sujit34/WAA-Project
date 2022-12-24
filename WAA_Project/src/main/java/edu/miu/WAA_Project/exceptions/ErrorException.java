package edu.miu.WAA_Project.exceptions;

public class ErrorException extends RuntimeException {
    String message;

    public ErrorException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
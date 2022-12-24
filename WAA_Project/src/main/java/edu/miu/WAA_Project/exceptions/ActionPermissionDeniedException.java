package edu.miu.WAA_Project.exceptions;

public class ActionPermissionDeniedException extends RuntimeException {
    String message;

    public ActionPermissionDeniedException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
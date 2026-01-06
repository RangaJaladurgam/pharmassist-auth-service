package com.pharmassist.auth.exception;

public class AdminAlreadyExistsByEmailException extends RuntimeException{

    private String message;

    public AdminAlreadyExistsByEmailException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

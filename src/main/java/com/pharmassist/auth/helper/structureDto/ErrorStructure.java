package com.pharmassist.auth.helper.structureDto;

import org.springframework.http.HttpStatus;

public class ErrorStructure<T> {

    private int status;
    private String message;
    private T rootCause;

    public ErrorStructure() {
    }

    public ErrorStructure(int status, String message, T rootCause) {
        this.status = status;
        this.message = message;
        this.rootCause = rootCause;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getRootCause() {
        return rootCause;
    }

    public void setRootCause(T rootCause) {
        this.rootCause = rootCause;
    }

    public static <T> ErrorStructure<T> create(HttpStatus status, String message, T rootCause){
        ErrorStructure<T> responseStructure = new ErrorStructure<>();
        responseStructure.setStatus(status.value());
        responseStructure.setMessage(message);
        responseStructure.setRootCause(rootCause);
        return responseStructure;
    }
}

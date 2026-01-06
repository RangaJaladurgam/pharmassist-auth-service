package com.pharmassist.auth.exceptionhander;


import com.pharmassist.auth.helper.structureDto.ErrorStructure;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class FieldErrorExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<ObjectError> errors = ex.getAllErrors();

        List<FieldErrorStructure> errorResponses = new ArrayList<>();

        for(ObjectError error : errors) {
            FieldError fr = (FieldError) error;

            String message = fr.getDefaultMessage();
            String field = fr.getField();
            Object rejectedValue = fr.getRejectedValue();

            FieldErrorStructure structure = new FieldErrorStructure(message, field, rejectedValue);

            errorResponses.add(structure);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorStructure.create(HttpStatus.BAD_REQUEST, "Invalid Input", errorResponses));
    }

}

class FieldErrorStructure{
    private String message;
    private String field;
    private Object rejectedValue;

    public FieldErrorStructure(String message, String field, Object rejectedValue) {
        super();
        this.message = message;
        this.field = field;
        this.rejectedValue = rejectedValue;
    }

    public String getMessage() {
        return message;
    }

    public String getField() {
        return field;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

}


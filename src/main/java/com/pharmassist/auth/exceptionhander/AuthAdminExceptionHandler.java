package com.pharmassist.auth.exceptionhander;

import com.pharmassist.auth.exception.AdminAlreadyExistsByEmailException;
import com.pharmassist.auth.exception.AdminNotFoundByIdException;
import com.pharmassist.auth.exception.NoAdminsFoundException;
import com.pharmassist.auth.helper.AuthAdminResponseBuilder;
import com.pharmassist.auth.helper.structureDto.ErrorStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthAdminExceptionHandler {

    @Autowired
    private AuthAdminResponseBuilder authAdminResponseBuilder;

    @ExceptionHandler(AdminNotFoundByIdException.class)
    public ResponseEntity<ErrorStructure<String>> handleAdminNotFoundById(AdminNotFoundByIdException ex){
        return authAdminResponseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "Admin not found by Id");
    }

    @ExceptionHandler(NoAdminsFoundException.class)
    public ResponseEntity<ErrorStructure<String>> handleAdminsNotFound(NoAdminsFoundException ex){
        return authAdminResponseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage(), "No Admins Found");
    }

    @ExceptionHandler(AdminAlreadyExistsByEmailException.class)
    public ResponseEntity<ErrorStructure<String>> handleAdminAlreadyExistsByEmail(AdminAlreadyExistsByEmailException ex){
        return authAdminResponseBuilder.error(HttpStatus.CONFLICT, ex.getMessage(), "Failed to register due to email already exists!");
    }
}

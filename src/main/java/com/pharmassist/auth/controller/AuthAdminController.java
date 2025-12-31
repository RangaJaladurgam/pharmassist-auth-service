package com.pharmassist.auth.controller;

import com.pharmassist.auth.helper.AuthAdminResponseBuilder;
import com.pharmassist.auth.helper.structureDto.ErrorStructure;
import com.pharmassist.auth.helper.structureDto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthAdminController {

    private final AuthAdminResponseBuilder authAdminResponseBuilder;

    public AuthAdminController(AuthAdminResponseBuilder authAdminResponseBuilder) {
        this.authAdminResponseBuilder = authAdminResponseBuilder;
    }

    @GetMapping
    public ResponseEntity<ErrorStructure<String>> hello(){
        return null;
    }





}

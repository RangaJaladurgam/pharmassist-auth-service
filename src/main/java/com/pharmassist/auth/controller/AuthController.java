package com.pharmassist.auth.controller;

import com.pharmassist.auth.helper.AuthAdminResponseBuilder;
import com.pharmassist.auth.helper.structureDto.ResponseStructure;
import com.pharmassist.auth.helper.structureDto.SimpleResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthAdminResponseBuilder authAdminResponseBuilder;

    @GetMapping("/")
    public ResponseEntity<SimpleResponseStructure> welcomeHome(){
        return authAdminResponseBuilder.success(HttpStatus.OK,"Welcome to Pharmassist API");
    }
}

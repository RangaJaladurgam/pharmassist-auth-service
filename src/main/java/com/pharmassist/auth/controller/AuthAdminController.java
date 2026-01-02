package com.pharmassist.auth.controller;

import com.pharmassist.auth.dto.AuthAdminRequestDto;
import com.pharmassist.auth.dto.AuthAdminResponseDto;
import com.pharmassist.auth.helper.AuthAdminResponseBuilder;
import com.pharmassist.auth.helper.structureDto.ErrorStructure;
import com.pharmassist.auth.helper.structureDto.ResponseStructure;
import com.pharmassist.auth.service.AuthAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthAdminController {

    private final AuthAdminResponseBuilder authAdminResponseBuilder;
    private final AuthAdminService authAdminService;

    public AuthAdminController(AuthAdminResponseBuilder authAdminResponseBuilder, AuthAdminService authAdminService) {
        this.authAdminResponseBuilder = authAdminResponseBuilder;
        this.authAdminService = authAdminService;
    }

    @GetMapping
    public String hello(){
        return "Working...";
    }


    @Operation(description = "The End-point can be used to save the Admin",
            responses = {
                    @ApiResponse(responseCode = "201",description = "Admin Created",
                            content = {
                                    @Content(schema = @Schema(implementation = AuthAdminResponseDto.class))
                            }

                    ),
                    @ApiResponse(responseCode = "400",description = "Bad AdminRequest, Invalid Input",
                            content = {
                                    @Content(schema = @Schema(implementation = ErrorStructure.class))
                            }
                    )
            }
    )
    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<AuthAdminResponseDto>> postAdmin(
            @RequestBody @Valid AuthAdminRequestDto adminRequest){
        AuthAdminResponseDto authAdminResponseDto = authAdminService.postAdmin(adminRequest);
        return authAdminResponseBuilder.success(HttpStatus.CREATED, "Admin Created", authAdminResponseDto);
    }





}

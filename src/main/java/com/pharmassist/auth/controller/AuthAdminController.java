package com.pharmassist.auth.controller;

import com.pharmassist.auth.dto.request.AuthAdminRequestDto;
import com.pharmassist.auth.dto.response.AuthAdminResponseDto;
import com.pharmassist.auth.dto.request.LoginRequest;
import com.pharmassist.auth.dto.response.AuthResponse;
import com.pharmassist.auth.helper.AuthAdminResponseBuilder;
import com.pharmassist.auth.helper.structureDto.ErrorStructure;
import com.pharmassist.auth.helper.structureDto.ResponseStructure;
import com.pharmassist.auth.security.jwt.JwtService;
import com.pharmassist.auth.service.impl.AuthAdminServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthAdminController {

    private final AuthAdminResponseBuilder authAdminResponseBuilder;
    private final AuthAdminServiceImpl authAdminService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthAdminController(AuthAdminResponseBuilder authAdminResponseBuilder, AuthAdminServiceImpl authAdminService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authAdminResponseBuilder = authAdminResponseBuilder;
        this.authAdminService = authAdminService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @GetMapping
    public String hello(){
        return "Working...";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            String token = jwtService.generateToken(loginRequest.getEmail());
            return authAdminResponseBuilder.success(HttpStatus.OK,"Login successful.",new AuthResponse(token));
        } catch (BadCredentialsException e) {
            return authAdminResponseBuilder.error(HttpStatus.UNAUTHORIZED,
                    "Invalid Credentials","Bad Credentials");
        }
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


    @Operation(description = "The End-point can be used to find the Admin",
            responses = {
                    @ApiResponse(responseCode = "302",description = "Admin Found",
                            content = {
                                    @Content(schema = @Schema(implementation = AuthAdminResponseDto.class))
                            }),
                    @ApiResponse(responseCode = "404",description = "Admin Not Found",
                            content = {
                                    @Content(schema = @Schema(implementation = ErrorStructure.class))
                            })
            }
    )
    @GetMapping("/profile")
    public ResponseEntity<ResponseStructure<AuthAdminResponseDto>> findAdmin(){
        AuthAdminResponseDto adminResponse = authAdminService.findAdmin();
        return authAdminResponseBuilder.success(HttpStatus.FOUND,"Admin found by Email", adminResponse);
    }

    @Operation(description = "The End-point can be used to List all the Admins",
            responses = {
                    @ApiResponse(responseCode = "302",description = "Admins Found",
                            content = {
                                    @Content(schema = @Schema(implementation = AuthAdminResponseDto.class))
                            }),
                    @ApiResponse(responseCode = "404",description = "No Admins Found",
                            content = {
                                    @Content(schema = @Schema(implementation = ErrorStructure.class))
                            })
            })
    @GetMapping("/admins")
    public ResponseEntity<ResponseStructure<List<AuthAdminResponseDto>>> findAllAdmins(){
        List<AuthAdminResponseDto> adminResponses = authAdminService.findAllAdmins();
        return authAdminResponseBuilder.success(HttpStatus.FOUND, "Admins Found", adminResponses);
    }

    @Operation(description = "The End-point can be used to update the Admin",
            responses = {
                    @ApiResponse(responseCode = "200",description = "Admin Updated",
                            content = {
                                    @Content(schema = @Schema(implementation = AuthAdminResponseDto.class))
                            }),
                    @ApiResponse(responseCode = "400",description = "Bad AdminRequest, Invalid Input",
                            content = {
                                    @Content(schema = @Schema(implementation = ErrorStructure.class))
                            }),
                    @ApiResponse(responseCode = "404",description = "Admin Not Found",
                            content = {
                                    @Content(schema = @Schema(implementation = ErrorStructure.class))
                            })
            })
    @PutMapping("/admins")
    public ResponseEntity<ResponseStructure<AuthAdminResponseDto>> updateAdmin(@RequestBody @Valid AuthAdminRequestDto adminRequest){
        AuthAdminResponseDto adminResponse = authAdminService.updateAdmin(adminRequest);
        return authAdminResponseBuilder.success(HttpStatus.OK, "Admin Updated", adminResponse);
    }

}

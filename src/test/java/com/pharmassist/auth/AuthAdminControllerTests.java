package com.pharmassist.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pharmassist.auth.controller.AuthAdminController;
import com.pharmassist.auth.dto.request.AuthAdminRequestDto;
import com.pharmassist.auth.dto.request.LoginRequest;
import com.pharmassist.auth.dto.response.AuthAdminResponseDto;
import com.pharmassist.auth.helper.AuthAdminResponseBuilder;
import com.pharmassist.auth.security.jwt.JwtService;
import com.pharmassist.auth.service.impl.AuthAdminServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthAdminController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthAdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthAdminServiceImpl authAdminService;

    @MockitoBean
    private AuthenticationManager authenticationManager;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private AuthAdminResponseBuilder authAdminResponseBuilder;

    private final ObjectMapper objectMapper = new ObjectMapper();



    @Test
    void login_shouldReturnToken_whenCredentialsAreValid() throws Exception {

        LoginRequest request = new LoginRequest();
        request.setEmail("ranga@gmail.com");
        request.setPassword("Ranga@1234");

        Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(Mockito.mock(org.springframework.security.core.Authentication.class));

        Mockito.when(jwtService.generateToken("ranga@gmail.com"))
                .thenReturn("token");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }



    @Test
    void register_shouldCreateAdmin() throws Exception {

        AuthAdminRequestDto request = new AuthAdminRequestDto();
        request.setEmail("admin12@gmail.com");
        request.setPassword("Admin@1234");
        request.setPhoneNumber("9500293323");

        AuthAdminResponseDto response = new AuthAdminResponseDto();
        response.setEmail("admin12@gmail.com");

        Mockito.when(authAdminService.postAdmin(Mockito.any()))
                .thenReturn(response);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    // ---------------- PROFILE ----------------

    @Test
    void findAdmin_shouldReturnAdminProfile() throws Exception {

        AuthAdminResponseDto response = new AuthAdminResponseDto();
        response.setEmail("ranga@gmail.com");

        Mockito.when(authAdminService.findAdmin())
                .thenReturn(response);

        mockMvc.perform(get("/api/auth/profile"))
                .andExpect(status().isOk());
    }

    // ---------------- FIND ALL ADMINS ----------------

    @Test
    void findAllAdmins_shouldReturnList() throws Exception {

        AuthAdminResponseDto admin = new AuthAdminResponseDto();
        admin.setEmail("ranga@gmail.com");

        Mockito.when(authAdminService.findAllAdmins())
                .thenReturn(List.of(admin));

        mockMvc.perform(get("/api/auth/admins"))
                .andExpect(status().isOk());
    }

    // ---------------- UPDATE ADMIN ----------------

    @Test
    void updateAdmin_shouldUpdateSuccessfully() throws Exception {

        AuthAdminRequestDto request = new AuthAdminRequestDto();
        request.setEmail("ranga@gmail.com");
        request.setPassword("Ranga@0987");
        request.setPhoneNumber("9500293323");

        AuthAdminResponseDto response = new AuthAdminResponseDto();
        response.setEmail("ranga@gmail.com");

        Mockito.when(authAdminService.updateAdmin(Mockito.any()))
                .thenReturn(response);

        mockMvc.perform(put("/api/auth/admins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}

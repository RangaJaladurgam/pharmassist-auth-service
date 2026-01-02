package com.pharmassist.auth.service;

import com.pharmassist.auth.dto.AuthAdminRequestDto;
import com.pharmassist.auth.dto.AuthAdminResponseDto;
import com.pharmassist.auth.exception.AdminNotFoundByIdException;
import com.pharmassist.auth.mapper.AuthAdminMapper;
import com.pharmassist.auth.model.Admin;
import com.pharmassist.auth.repository.AuthAdminRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthAdminService {

    private final AuthAdminRepository authAdminRepository;
    private final AuthAdminMapper authAdminMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthAdminService(AuthAdminRepository authAdminRepository, AuthAdminMapper authAdminMapper, PasswordEncoder passwordEncoder) {
        this.authAdminRepository = authAdminRepository;
        this.authAdminMapper = authAdminMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthAdminResponseDto postAdmin(AuthAdminRequestDto adminRequest) {
        Admin admin = authAdminMapper.mapToAdmin(adminRequest, new Admin());
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin = authAdminRepository.save(admin);

        return authAdminMapper.mapToAdminResponse(admin,null);
    }

    public AuthAdminResponseDto findAdmin() {
        String email = getCurrentLoggedInUser();
        return authAdminRepository.findByEmail(email)
                .map((admin)-> authAdminMapper.mapToAdminResponse(admin,null))
                .orElseThrow(()-> new AdminNotFoundByIdException("Failed to find the Admin"));
    }

    private String getCurrentLoggedInUser() {
        return Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();
    }
}

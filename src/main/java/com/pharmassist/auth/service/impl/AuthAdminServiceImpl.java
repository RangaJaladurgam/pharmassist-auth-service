package com.pharmassist.auth.service.impl;

import com.pharmassist.auth.dto.request.AuthAdminRequestDto;
import com.pharmassist.auth.dto.response.AuthAdminResponseDto;
import com.pharmassist.auth.dto.response.PharmacyResponseDto;
import com.pharmassist.auth.exception.AdminNotFoundByIdException;
import com.pharmassist.auth.exception.NoAdminsFoundException;
import com.pharmassist.auth.mapper.AuthAdminMapper;
import com.pharmassist.auth.model.Admin;
import com.pharmassist.auth.repository.AuthAdminRepository;
import com.pharmassist.auth.service.AuthAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class AuthAdminServiceImpl implements AuthAdminService {

    private Logger log = LoggerFactory.getLogger(AuthAdminService.class);

    private final AuthAdminRepository authAdminRepository;
    private final AuthAdminMapper authAdminMapper;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;

    public AuthAdminServiceImpl(AuthAdminRepository authAdminRepository, AuthAdminMapper authAdminMapper, PasswordEncoder passwordEncoder, RestTemplate restTemplate) {
        this.authAdminRepository = authAdminRepository;
        this.authAdminMapper = authAdminMapper;
        this.passwordEncoder = passwordEncoder;
        this.restTemplate = restTemplate;
    }

    public AuthAdminResponseDto postAdmin(AuthAdminRequestDto adminRequest) {
        Admin admin = authAdminMapper.mapToAdmin(adminRequest, new Admin());
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin = authAdminRepository.save(admin);
        log.info("Admin Created Successfully...");
        return authAdminMapper.mapToAdminResponse(admin,null);
    }

    public AuthAdminResponseDto findAdmin() {
        String email = getCurrentLoggedInUser();
        return authAdminRepository.findByEmail(email)
                .map((admin)-> {
                    return authAdminMapper.mapToAdminResponse(admin, getPharmacyResponseDtoById(admin.getPharmacyId()));
                })
                .orElseThrow(()-> new AdminNotFoundByIdException("Failed to find the Admin"));
    }

    public List<AuthAdminResponseDto> findAllAdmins() {
        List<Admin> admins = authAdminRepository.findAll();
        if(admins.isEmpty())
            throw new NoAdminsFoundException("Failed to find all Admins");

        return admins.stream()
                .map(admin -> {
                    return authAdminMapper.mapToAdminResponse(admin, getPharmacyResponseDtoById(admin.getPharmacyId()));
                })
                .toList();
    }

    public AuthAdminResponseDto updateAdmin(AuthAdminRequestDto adminRequest) {
        String email = getCurrentLoggedInUser();
        return authAdminRepository.findByEmail(email)
                .map((exAdmin)->{
                    exAdmin = authAdminMapper.mapToAdmin(adminRequest, exAdmin);
                    exAdmin.setPassword(passwordEncoder.encode(exAdmin.getPassword()));
                    return authAdminRepository.save(exAdmin);
                })
                .map(admin -> authAdminMapper.mapToAdminResponse(admin,getPharmacyResponseDtoById(admin.getPharmacyId())))
                .orElseThrow(()-> new AdminNotFoundByIdException("Failed to Update the Admin"));

    }

    private String getCurrentLoggedInUser() {
        return Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();
    }

    private PharmacyResponseDto getPharmacyResponseDtoById(String pharmacyId){
            PharmacyResponseDto pharmacyResponseDto = null;
            try {
                pharmacyResponseDto = restTemplate.getForEntity(
                        "http://localhost:8180/api/pharmacy/" + pharmacyId,
                        PharmacyResponseDto.class
                ).getBody();
            } catch (Exception e) {

                System.err.println("Failed to fetch pharmacy for adminId=" + pharmacyId + ": " + e.getMessage());
                log.error("Failed to fetch pharmacy for adminId={}: {}", pharmacyId, e.getMessage());
            }
            return pharmacyResponseDto;
    }
    private void ok(){
        List l = List.of(1,"raj");
        System.out.println(l);


    }
}

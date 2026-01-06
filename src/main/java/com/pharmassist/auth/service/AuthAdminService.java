package com.pharmassist.auth.service;

import com.pharmassist.auth.dto.request.AuthAdminRequestDto;
import com.pharmassist.auth.dto.response.AuthAdminResponseDto;

import java.util.List;

public interface AuthAdminService {

    public AuthAdminResponseDto updateAdmin(AuthAdminRequestDto adminRequest);
    public AuthAdminResponseDto postAdmin(AuthAdminRequestDto adminRequest);
    public AuthAdminResponseDto findAdmin();
    public List<AuthAdminResponseDto> findAllAdmins();
}

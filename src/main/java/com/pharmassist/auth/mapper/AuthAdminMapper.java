package com.pharmassist.auth.mapper;

import com.pharmassist.auth.dto.request.AuthAdminRequestDto;
import com.pharmassist.auth.dto.response.AuthAdminResponseDto;
import com.pharmassist.auth.dto.response.PharmacyResponseDto;
import com.pharmassist.auth.model.Admin;
import org.springframework.stereotype.Component;

@Component
public class AuthAdminMapper {

    public Admin mapToAdmin(AuthAdminRequestDto adminRequest, Admin admin) {
        admin.setEmail(adminRequest.getEmail());
        admin.setPassword(adminRequest.getPassword());
        admin.setPhoneNumber(adminRequest.getPhoneNumber());
        return admin;
    }

    public AuthAdminResponseDto mapToAdminResponse(Admin admin, PharmacyResponseDto pharmacyResponseDto) {
        AuthAdminResponseDto adminResponse = new AuthAdminResponseDto();
        adminResponse.setAdminId(admin.getAdminId());
        adminResponse.setEmail(admin.getEmail());
        adminResponse.setPhoneNumber(admin.getPhoneNumber());
        if(admin.getPharmacyId()==null)
            adminResponse.setPharmacyResponse(null);
        else
            adminResponse.setPharmacyResponse(pharmacyResponseDto);
        return adminResponse;
    }

}

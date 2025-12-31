package com.pharmassist.auth.dto;

public class AuthAdminResponseDto {

    private String adminId;
    private String email;
    private String phoneNumber;
    private PharmacyResponseDto pharmacyResponse;

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getAdminId() {
        return adminId;
    }
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public PharmacyResponseDto getPharmacyResponse() {
        return pharmacyResponse;
    }
    public void setPharmacyResponse(PharmacyResponseDto pharmacyResponse) {
        this.pharmacyResponse = pharmacyResponse;
    }

}

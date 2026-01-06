package com.pharmassist.auth.model;

import com.pharmassist.auth.config.CustomId;
import jakarta.persistence.*;

@Entity
@Table(
        name = "ADMINS",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        }
)
public class Admin {

    @Id
    @CustomId
    @Column(name = "admin_id")
    private String adminId;

    @Column(name = "email",nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "pharmacy_id")
    private String pharmacyId;

    public Admin() {
    }

    public Admin(String email, String phoneNumber, String password, String pharmacyId) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.pharmacyId = pharmacyId;
    }


    public String getAdminId() {
        return adminId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(String pharmacyId) {
        this.pharmacyId = pharmacyId;
    }
}

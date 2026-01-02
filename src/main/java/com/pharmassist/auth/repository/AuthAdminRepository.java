package com.pharmassist.auth.repository;

import com.pharmassist.auth.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthAdminRepository extends JpaRepository<Admin,String> {

    Optional<Admin> findByEmail(String email);
}

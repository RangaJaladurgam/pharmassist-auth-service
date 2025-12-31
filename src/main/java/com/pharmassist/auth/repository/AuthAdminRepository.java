package com.pharmassist.auth.repository;

import com.pharmassist.auth.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthAdminRepository extends JpaRepository<Admin,String> {

}

package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.library.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findByAdminNumber(String adminNumber);

}//end class AdminRepository
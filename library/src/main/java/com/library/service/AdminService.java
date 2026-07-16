package com.library.service;

import com.library.Admin;
import com.library.repository.AdminRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    //---------- CONSTRUCTOR ----------
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    //---------- ADMIN MANAGEMENT ----------
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Integer id) {
        return adminRepository.findById(id).orElse(null);
    }

    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Integer id, Admin updatedAdmin) {
        Admin admin = adminRepository.findById(id).orElse(null);

        if(admin == null) {
            return null;
        }

        admin.setName(updatedAdmin.getName());
        admin.setEmail(updatedAdmin.getEmail());
        admin.setPhoneNumber(updatedAdmin.getPhoneNumber());

        return adminRepository.save(admin);
    }

    public void deleteAdmin(Integer id) {
        adminRepository.deleteById(id);
    }

    //---------- LOGIN ----------
    public Admin login(String adminNumber, String password) {
        Admin admin = adminRepository.findByAdminNumber(adminNumber);

        if(admin == null) {
            return null;
        }

        if(!admin.getPassword().equals(password)) {
            return null;
        }

        return admin;
    }

    //---------- UPDATE PROFILE ----------
    public Admin changePassword(Integer id, String newPassword) {
        Admin admin = adminRepository.findById(id).orElse(null);

        if(admin == null) {
            return null;
        }

        admin.setPassword(newPassword);

        return adminRepository.save(admin);
    }

}//end class AdminService
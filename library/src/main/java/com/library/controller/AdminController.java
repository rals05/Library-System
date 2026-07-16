package com.library.controller;

import com.library.Admin;
import com.library.dto.LoginRequest;
import com.library.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService adminService;

    //---------- CONSTRUCTOR ----------
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    //---------- METHODS ----------
    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Integer id) {
        Admin admin = adminService.getAdminById(id);

        if(admin == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(admin);
    }

    @PostMapping
    public Admin addAdmin(@RequestBody Admin admin) {
        return adminService.addAdmin(admin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Integer id,
                                             @RequestBody Admin updatedAdmin) {

        Admin admin = adminService.updateAdmin(id, updatedAdmin);

        if(admin == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(admin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Integer id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Admin> login(@RequestBody LoginRequest loginRequest) {

        Admin admin = adminService.login(
                loginRequest.getNumber(),
                loginRequest.getPassword());

        if(admin == null) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(admin);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Admin> changePassword(@PathVariable Integer id,
                                                @RequestParam String newPassword) {

        Admin admin = adminService.changePassword(id, newPassword);

        if(admin == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(admin);
    }

}//end class AdminController
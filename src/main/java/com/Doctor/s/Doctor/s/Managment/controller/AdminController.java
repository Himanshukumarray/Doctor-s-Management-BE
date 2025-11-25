package com.Doctor.s.Doctor.s.Managment.controller;

import com.Doctor.s.Doctor.s.Managment.entity.UserEntity;
import com.Doctor.s.Doctor.s.Managment.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // All pending doctors
    @GetMapping("/pending-doctors")
    public List<UserEntity> getPendingDoctors() {
        return adminService.getPendingDoctors();
    }

    // Approve doctor
    @PutMapping("/approve/{id}")
    public UserEntity approveDoctor(@PathVariable Long id) {
        return adminService.approveDoctor(id);
    }

    // Reject doctor
    @PutMapping("/reject/{id}")
    public UserEntity rejectDoctor(@PathVariable Long id) {
        return adminService.rejectDoctor(id);
    }

    @GetMapping("/all-doctors")
    public List<UserEntity> getAllDoctors() {
        return adminService.getAllDoctors();
    }

    @GetMapping("/all-patients")
    public List<UserEntity> getAllPatients() {
        return adminService.getAllPatients();
    }

}

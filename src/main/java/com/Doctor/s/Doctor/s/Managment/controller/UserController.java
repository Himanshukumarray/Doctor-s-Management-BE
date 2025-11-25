package com.Doctor.s.Doctor.s.Managment.controller;
import com.Doctor.s.Doctor.s.Managment.entity.UserEntity;
import com.Doctor.s.Doctor.s.Managment.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // doctors
    @GetMapping("/doctors")
    public List<UserEntity> getAllDoctors() {
        return userService.getAllDoctors();
    }

    @GetMapping("/doctors/{id}")
    public UserEntity getDoctorById(@PathVariable Long id) {
        return userService.getDoctorById(id);
    }

    @GetMapping("/doctors/specialty/{specialty}")
    public List<UserEntity> getDoctorsBySpecialty(@PathVariable String specialty) {
        return userService.getDoctorsBySpecialty(specialty);
    }

    // patients
    @GetMapping("/patients")
    public List<UserEntity> getAllPatients() {
        return userService.getAllPatients();
    }

    @GetMapping("/patients/{id}")
    public UserEntity getPatientById(@PathVariable Long id) {
        return userService.getPatientById(id);
    }
}

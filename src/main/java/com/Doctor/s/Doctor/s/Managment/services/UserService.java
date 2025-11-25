package com.Doctor.s.Doctor.s.Managment.services;

import com.Doctor.s.Doctor.s.Managment.entity.UserEntity;
import com.Doctor.s.Doctor.s.Managment.entity.UserStatus;
import com.Doctor.s.Doctor.s.Managment.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private AuthRepository authRepository;

    // ---------------- YOUR ORIGINAL METHODS (UNCHANGED) ----------------

    public UserEntity createDoctor(UserEntity doctor){
        return authRepository.save(doctor);
    }

    public List<UserEntity> getAll(){
        return authRepository.findAll();
    }

    public UserEntity getById(Long id){
        return authRepository.findById(id).orElse(null);
    }

    // ---------------- NEW METHODS (ADDED ONLY) ----------------

    // Create patient
    public UserEntity createPatient(UserEntity patient) {
        return authRepository.save(patient);
    }

    // Get all doctors
    public List<UserEntity> getAllDoctors() {
        return authRepository.findByRole("DOCTOR");
    }

    // Get all patients
    public List<UserEntity> getAllPatients() {
        return authRepository.findByRole("PATIENT");
    }

    // Get doctor by ID
    public UserEntity getDoctorById(Long id) {
        UserEntity user = authRepository.findById(id).orElse(null);
        if (user != null && "DOCTOR".equals(user.getRole())) {
            return user;
        }
        return null;
    }

    // Get patient by ID
    public UserEntity getPatientById(Long id) {
        UserEntity user = authRepository.findById(id).orElse(null);
        if (user != null && "PATIENT".equals(user.getRole())) {
            return user;
        }
        return null;
    }

    // Get doctors by specialty
    public List<UserEntity> getDoctorsBySpecialty(String specialty) {
        return authRepository.findByRoleAndSpecialtyAndStatus("DOCTOR", specialty, UserStatus.APPROVED);
    }
}

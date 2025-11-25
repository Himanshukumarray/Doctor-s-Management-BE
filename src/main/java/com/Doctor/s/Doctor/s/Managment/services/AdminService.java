package com.Doctor.s.Doctor.s.Managment.services;

import com.Doctor.s.Doctor.s.Managment.entity.UserEntity;
import com.Doctor.s.Doctor.s.Managment.entity.UserStatus;
import com.Doctor.s.Doctor.s.Managment.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AuthRepository authRepository;

    // Get all pending doctor requests
    public List<UserEntity> getPendingDoctors() {
        return authRepository.findByRoleAndStatus("DOCTOR", UserStatus.PENDING);
    }

    // Approve doctor
    public UserEntity approveDoctor(Long id) {
        UserEntity doctor = authRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        if (!"DOCTOR".equalsIgnoreCase(doctor.getRole())) {
            throw new RuntimeException("User is not a doctor");
        }

        doctor.setStatus(UserStatus.APPROVED);
        return authRepository.save(doctor);
    }

    // Reject doctor
    public UserEntity rejectDoctor(Long id) {
        UserEntity doctor = authRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        if (!"DOCTOR".equalsIgnoreCase(doctor.getRole())) {
            throw new RuntimeException("User is not a doctor");
        }

        doctor.setStatus(UserStatus.REJECTED);
        return authRepository.save(doctor);
    }

    public List<UserEntity> getAllDoctors() {
        return authRepository.findByRole("DOCTOR");
    }

    public List<UserEntity> getAllPatients() {
        return authRepository.findByRole("PATIENT");
    }

}

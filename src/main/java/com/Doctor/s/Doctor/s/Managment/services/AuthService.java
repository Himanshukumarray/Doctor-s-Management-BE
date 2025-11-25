package com.Doctor.s.Doctor.s.Managment.services;

import com.Doctor.s.Doctor.s.Managment.dto.LoginDto;
import com.Doctor.s.Doctor.s.Managment.dto.SignUpDto;
import com.Doctor.s.Doctor.s.Managment.entity.UserEntity;
import com.Doctor.s.Doctor.s.Managment.entity.UserStatus;
import com.Doctor.s.Doctor.s.Managment.repository.AuthRepository;
import com.Doctor.s.Doctor.s.Managment.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;


    // ---------------------------------------------------------
    // LOGIN METHOD
    // ---------------------------------------------------------
    public Map<String, String> login(LoginDto loginDto) {
        Optional<UserEntity> optUser = authRepository.findByEmail(loginDto.getEmail());
        Map<String, String> response = new HashMap<>();

        if (optUser.isEmpty()) {
            response.put("error", "Invalid email or password!");
            return response;
        }

        UserEntity user = optUser.get();

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            response.put("error", "Invalid email or password!");
            return response;
        }

        String role = user.getRole() != null ? user.getRole().toUpperCase() : "USER";

        if (user.getStatus() != UserStatus.APPROVED) {
            response.put("error", "Your account is not approved!");
            return response;
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role))
        );

        String token = jwtUtil.generateToken(userDetails, role);
        Long Id = user.getId();

        response.put("token", "Bearer "+ token);
        response.put("role", role);
        response.put("email", user.getEmail());
        response.put("id", String.valueOf(Id));
        ;
        return response;
    }

    public String register(SignUpDto signUpDto) {

        // Validate email
        Optional<UserEntity> existingUser = authRepository.findByEmail(signUpDto.getEmail());
        if (existingUser.isPresent()) {
            return "Email already registered!";
        }

        // Create new user entity
        UserEntity newUser = new UserEntity();

        // Set status based on role
        if ("DOCTOR".equalsIgnoreCase(signUpDto.getRole())) {
            newUser.setStatus(UserStatus.PENDING);  // doctors must be approved
        } else {
            newUser.setStatus(UserStatus.APPROVED); // patient auto-approved
        }

        // -------- COMMON FIELDS --------
        newUser.setName(signUpDto.getName());
        newUser.setEmail(signUpDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        newUser.setRole(signUpDto.getRole());
        newUser.setAge(signUpDto.getAge());
        newUser.setGender(signUpDto.getGender());
        newUser.setPhone(signUpDto.getPhone());
        newUser.setAddress(signUpDto.getAddress());

        // -------- DOCTOR FIELDS --------
        if ("DOCTOR".equalsIgnoreCase(signUpDto.getRole())) {

            newUser.setSpecialty(signUpDto.getSpecialty());
            newUser.setFee(signUpDto.getFee());
            newUser.setQualification(signUpDto.getQualification());
            newUser.setExperienceYears(signUpDto.getExperienceYears());
            newUser.setLicenseNumber(signUpDto.getLicenseNumber());
            newUser.setAvailability(signUpDto.getAvailability());
        }

        // -------- PATIENT FIELDS --------
        if ("PATIENT".equalsIgnoreCase(signUpDto.getRole())) {

            newUser.setBloodGroup(signUpDto.getBloodGroup());
            newUser.setEmergencyContactName(signUpDto.getEmergencyContactName());
            newUser.setEmergencyContactPhone(signUpDto.getEmergencyContactPhone());
            newUser.setMedicalHistory(signUpDto.getMedicalHistory());
            newUser.setAllergies(signUpDto.getAllergies());
            newUser.setInsuranceProvider(signUpDto.getInsuranceProvider());
            newUser.setInsuranceNumber(signUpDto.getInsuranceNumber());
        }

        authRepository.save(newUser);
        return "Registration successful!";
    }
}

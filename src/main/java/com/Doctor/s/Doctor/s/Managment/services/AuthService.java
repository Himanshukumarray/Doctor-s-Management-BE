package com.Doctor.s.Doctor.s.Managment.services;

import com.Doctor.s.Doctor.s.Managment.dto.LoginDto;
import com.Doctor.s.Doctor.s.Managment.dto.SignUpDto;
import com.Doctor.s.Doctor.s.Managment.entity.UserEntity;
import com.Doctor.s.Doctor.s.Managment.repository.AuthRepository;
import com.Doctor.s.Doctor.s.Managment.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;


    public String login(LoginDto loginDto) {
        UserEntity user = authRepository.findByEmail(loginDto.getEmail());

        if (user != null && passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            // ✅ Generate JWT token
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    new ArrayList<>()
            );

            String token = jwtUtil.generateToken(userDetails);


            return token;
        } else {
            return "Invalid email or password!";
        }
    }


    public String register(SignUpDto signUpDto) {
        // Check if email already exists
        UserEntity existingUser = authRepository.findByEmail(signUpDto.getEmail());
        if (existingUser != null) {
            return "Email already registered!";
        }

        // Create new user
        UserEntity newUser = new UserEntity();
        newUser.setName(signUpDto.getName());
        newUser.setEmail(signUpDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        newUser.setRole(signUpDto.getRole());

        authRepository.save(newUser);
        return "Registration successful!";
    }
}

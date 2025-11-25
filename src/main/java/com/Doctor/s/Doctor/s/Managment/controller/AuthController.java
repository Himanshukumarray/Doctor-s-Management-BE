package com.Doctor.s.Doctor.s.Managment.controller;

import com.Doctor.s.Doctor.s.Managment.dto.LoginDto;
import com.Doctor.s.Doctor.s.Managment.dto.SignUpDto;
import com.Doctor.s.Doctor.s.Managment.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {

        Map<String, String> response = authService.login(loginDto);

        // If authentication failed
        if (response.containsKey("error")) {
            return ResponseEntity.status(401).body(response);
        }

        // Success → token returned
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpDto signUpDto) {
        String response = authService.register(signUpDto);
        return ResponseEntity.ok(response);
    }
}

package com.Doctor.s.Doctor.s.Managment.security;
import com.Doctor.s.Doctor.s.Managment.entity.UserEntity;
import com.Doctor.s.Doctor.s.Managment.entity.UserStatus;
import com.Doctor.s.Doctor.s.Managment.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Seed ADMIN if not exists (email/admin@example.com)
        Optional<UserEntity> adminOpt = authRepository.findByEmail("admin@gmail.com");
        if (adminOpt.isEmpty()) {
            UserEntity admin = new UserEntity();
            admin.setName("Administrator");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("Admin@123")); // change password
            admin.setRole("ADMIN");
            admin.setStatus(UserStatus.APPROVED);
            authRepository.save(admin);
            System.out.println("Seeded ADMIN: admin@gmail.com / Admin@123");
        }

        // Optional: set null statuses to APPROVED for existing PATIENTs, to prevent lockouts
        authRepository.findAll().forEach(u -> {
            if (u.getStatus() == null) {
                if ("PATIENT".equalsIgnoreCase(u.getRole())) {
                    u.setStatus(UserStatus.APPROVED);
                } else if ("DOCTOR".equalsIgnoreCase(u.getRole())) {
                    // keep doctors pending by default if you prefer, or APPROVED
                    u.setStatus(UserStatus.PENDING);
                } else {
                    u.setStatus(UserStatus.APPROVED);
                }
                authRepository.save(u);
            }
        });
    }
}

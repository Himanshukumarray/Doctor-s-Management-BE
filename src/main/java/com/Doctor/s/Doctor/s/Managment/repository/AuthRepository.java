package com.Doctor.s.Doctor.s.Managment.repository;

import com.Doctor.s.Doctor.s.Managment.entity.UserEntity;
import com.Doctor.s.Doctor.s.Managment.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    // convenience: get all doctors with a given status
    List<UserEntity> findByRoleAndStatus(String role, UserStatus status);

    List<UserEntity> findByRole(String role);

    List<UserEntity> findByRoleAndSpecialtyAndStatus(String doctor, String specialty, UserStatus status);
}

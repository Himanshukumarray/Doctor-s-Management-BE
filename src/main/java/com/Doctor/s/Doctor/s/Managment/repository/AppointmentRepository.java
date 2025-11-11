package com.Doctor.s.Doctor.s.Managment.repository;

import com.Doctor.s.Doctor.s.Managment.entity.AppointmentEntity;
import com.Doctor.s.Doctor.s.Managment.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    // Get all appointments for a specific doctor
    List<AppointmentEntity> findByDoctor(UserEntity doctor);

    // Get all appointments for a specific patient
    List<AppointmentEntity> findByPatient(UserEntity patient);

    // Get all appointments with specific status (e.g., "PENDING", "APPROVED", "REJECTED")
    List<AppointmentEntity> findByStatus(String status);
}

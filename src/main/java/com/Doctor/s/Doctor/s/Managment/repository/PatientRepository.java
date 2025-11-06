package com.Doctor.s.Doctor.s.Managment.repository;

import com.Doctor.s.Doctor.s.Managment.Entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<PatientEntity,Long> {
}

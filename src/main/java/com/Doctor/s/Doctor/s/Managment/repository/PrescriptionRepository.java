package com.Doctor.s.Doctor.s.Managment.repository;

import com.Doctor.s.Doctor.s.Managment.entity.PrescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<PrescriptionEntity,Long> {
}
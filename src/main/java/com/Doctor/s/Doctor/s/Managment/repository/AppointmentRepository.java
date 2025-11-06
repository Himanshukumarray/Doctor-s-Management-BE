package com.Doctor.s.Doctor.s.Managment.repository;

import com.Doctor.s.Doctor.s.Managment.Entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity,Long> {
}

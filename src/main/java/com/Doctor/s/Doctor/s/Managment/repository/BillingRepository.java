package com.Doctor.s.Doctor.s.Managment.repository;

import com.Doctor.s.Doctor.s.Managment.entity.BillingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillingRepository extends JpaRepository<BillingEntity, Long> {
    List<BillingEntity> findByAppointment_Patient_Id(Long patientId);
    List<BillingEntity> findByAppointment_Doctor_Id(Long doctorId);
}

package com.Doctor.s.Doctor.s.Managment.controller;

import com.Doctor.s.Doctor.s.Managment.dto.PrescriptionRequestDto;
import com.Doctor.s.Doctor.s.Managment.entity.PrescriptionEntity;
import com.Doctor.s.Doctor.s.Managment.services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping("/create/{appointmentId}")
    public PrescriptionEntity createPrescription(
            @PathVariable Long appointmentId,
            @RequestBody PrescriptionRequestDto request
    ) throws Exception {

        return prescriptionService.createPrescription(appointmentId, request);
    }
}


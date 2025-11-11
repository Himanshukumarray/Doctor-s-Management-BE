package com.Doctor.s.Doctor.s.Managment.controller;

import com.Doctor.s.Doctor.s.Managment.dto.AppointmentRequestDto;
import com.Doctor.s.Doctor.s.Managment.dto.AppointmentResponseDto;
import com.Doctor.s.Doctor.s.Managment.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // ✅ 1. Book appointment (Patient)
    @PostMapping("/book")
    public ResponseEntity<AppointmentResponseDto> bookAppointment(@RequestBody AppointmentRequestDto dto) {
        AppointmentResponseDto response = appointmentService.bookAppointment(dto);
        return ResponseEntity.ok(response);
    }

    // ✅ 2. Get appointments for doctor
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        List<AppointmentResponseDto> appointments = appointmentService.getAppointmentsByDoctor(doctorId);
        return ResponseEntity.ok(appointments);
    }

    // ✅ 3. Get appointments for patient
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByPatient(@PathVariable Long patientId) {
        List<AppointmentResponseDto> appointments = appointmentService.getAppointmentsByPatient(patientId);
        return ResponseEntity.ok(appointments);
    }

    // ✅ 4. Update appointment status (Approve / Reject)
    @PutMapping("/{appointmentId}/status")
    public ResponseEntity<AppointmentResponseDto> updateStatus(
            @PathVariable Long appointmentId,
            @RequestParam String status) {
        AppointmentResponseDto updated = appointmentService.updateStatus(appointmentId, status);
        return ResponseEntity.ok(updated);
    }
}

package com.Doctor.s.Doctor.s.Managment.controller;
import com.Doctor.s.Doctor.s.Managment.dto.AppointmentRequestDto;
import com.Doctor.s.Doctor.s.Managment.dto.AppointmentResponseDto;
import com.Doctor.s.Doctor.s.Managment.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/book")
    public ResponseEntity<AppointmentResponseDto> bookAppointment(@RequestBody AppointmentRequestDto dto) {
        AppointmentResponseDto response = appointmentService.bookAppointment(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        List<AppointmentResponseDto> appointments = appointmentService.getAppointmentsByDoctor(doctorId);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByPatient(@PathVariable Long patientId) {
        List<AppointmentResponseDto> appointments = appointmentService.getAppointmentsByPatient(patientId);
        return ResponseEntity.ok(appointments);
    }

    @PutMapping("/{appointmentId}/status")
    public ResponseEntity<AppointmentResponseDto> updateStatus(
            @PathVariable Long appointmentId,
            @RequestParam String status) {
        AppointmentResponseDto updated = appointmentService.updateStatus(appointmentId, status);
        return ResponseEntity.ok(updated);
    }
}

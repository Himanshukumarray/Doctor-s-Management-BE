package com.Doctor.s.Doctor.s.Managment.services;
import com.Doctor.s.Doctor.s.Managment.dto.AppointmentRequestDto;
import com.Doctor.s.Doctor.s.Managment.dto.AppointmentResponseDto;
import com.Doctor.s.Doctor.s.Managment.entity.AppointmentEntity;
import com.Doctor.s.Doctor.s.Managment.entity.UserEntity;
import com.Doctor.s.Doctor.s.Managment.repository.AppointmentRepository;
import com.Doctor.s.Doctor.s.Managment.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AuthRepository authRepository;

    // ✅ 1. Book an appointment (Patient)
    public AppointmentResponseDto bookAppointment(AppointmentRequestDto dto) {
        UserEntity doctor = authRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found!"));
        UserEntity patient = authRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found!"));

        AppointmentEntity appointment = new AppointmentEntity();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setDescription(dto.getDescription());
        appointment.setStatus("PENDING");

        AppointmentEntity saved = appointmentRepository.save(appointment);

        return mapToDto(saved);
    }

    // ✅ 2. Get all appointments for a doctor
    public List<AppointmentResponseDto> getAppointmentsByDoctor(Long doctorId) {
        UserEntity doctor = authRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found!"));
        return appointmentRepository.findByDoctor(doctor)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ✅ 3. Get all appointments for a patient
    public List<AppointmentResponseDto> getAppointmentsByPatient(Long patientId) {
        UserEntity patient = authRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found!"));
        return appointmentRepository.findByPatient(patient)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ✅ 4. Update appointment status (Approve / Reject)
    public AppointmentResponseDto updateStatus(Long appointmentId, String status) {
        AppointmentEntity appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found!"));
        appointment.setStatus(status.toUpperCase());
        AppointmentEntity updated = appointmentRepository.save(appointment);
        return mapToDto(updated);
    }

    // ✅ 5. Utility mapper method
    private AppointmentResponseDto mapToDto(AppointmentEntity appointment) {
        AppointmentResponseDto dto = new AppointmentResponseDto();
        dto.setId(appointment.getId());
        dto.setDoctorName(appointment.getDoctor().getName());
        dto.setPatientName(appointment.getPatient().getName());
        dto.setAppointmentDate(appointment.getAppointmentDate());
        dto.setAppointmentTime(appointment.getAppointmentTime());
        dto.setDescription(appointment.getDescription());
        dto.setStatus(appointment.getStatus());
        dto.setCreatedAt(appointment.getCreatedAt());
        return dto;
    }
}

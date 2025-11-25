package com.Doctor.s.Doctor.s.Managment.services;

import com.Doctor.s.Doctor.s.Managment.dto.PrescriptionRequestDto;
import com.Doctor.s.Doctor.s.Managment.entity.AppointmentEntity;
import com.Doctor.s.Doctor.s.Managment.entity.PrescriptionEntity;
import com.Doctor.s.Doctor.s.Managment.repository.AppointmentRepository;
import com.Doctor.s.Doctor.s.Managment.repository.PrescriptionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private ObjectMapper objectMapper;


    public PrescriptionEntity createPrescription(Long appointmentId, PrescriptionRequestDto request) throws Exception {

        // 1. Fetch appointment
        AppointmentEntity appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        // 2. Create new prescription object
        PrescriptionEntity prescription = new PrescriptionEntity();

        // -------- Copy appointment data --------
        prescription.setPatientId(appointment.getPatient().getId());
        prescription.setDoctorId(appointment.getDoctor().getId());
        prescription.setAppointmentDate(appointment.getAppointmentDate());
        prescription.setAppointmentTime(appointment.getAppointmentTime());
        prescription.setDescription(appointment.getDescription());
        prescription.setAppointmentCreatedAt(appointment.getCreatedAt());

        // -------- Doctor-filled data --------
        prescription.setDiagnosis(request.getDiagnosis());
        prescription.setSymptoms(request.getSymptoms());
        prescription.setAdvice(request.getAdvice());
        prescription.setFollowUpDate(request.getFollowUpDate());

        // Convert medicines list to JSON string
        String medicinesJson = objectMapper.writeValueAsString(request.getMedicines());
        prescription.setMedicines(medicinesJson);

        // 3. Save prescription
        PrescriptionEntity saved = prescriptionRepository.save(prescription);

        // 4. Update appointment status (optional)
        appointment.setStatus("COMPLETED");
        appointmentRepository.save(appointment);

        return saved;
    }
}


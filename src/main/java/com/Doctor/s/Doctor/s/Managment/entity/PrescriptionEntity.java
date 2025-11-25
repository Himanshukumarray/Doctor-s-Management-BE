package com.Doctor.s.Doctor.s.Managment.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "prescriptions")
public class PrescriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // -------- Copied from Appointment --------
    private Long patientId;
    private Long doctorId;

    private LocalDate appointmentDate;
    private LocalTime appointmentTime;

    @Column(length = 1000)
    private String description;

    private LocalDateTime appointmentCreatedAt;


    // -------- Medical Details --------
    @Column(length = 2000)
    private String diagnosis;

    @Column(length = 2000)
    private String symptoms;

    @Column(columnDefinition = "TEXT")
    private String medicines;
    // 👈 contains JSON string with: Medicine, Schedule, Instructions, Route, Days


    @Column(length = 2000)
    private String advice;

    private LocalDate followUpDate;

    private LocalDateTime createdAt;


    public PrescriptionEntity() {
        this.createdAt = LocalDateTime.now();
    }

    // getters & setters...

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getAppointmentCreatedAt() {
        return appointmentCreatedAt;
    }

    public void setAppointmentCreatedAt(LocalDateTime appointmentCreatedAt) {
        this.appointmentCreatedAt = appointmentCreatedAt;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public LocalDate getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(LocalDate followUpDate) {
        this.followUpDate = followUpDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

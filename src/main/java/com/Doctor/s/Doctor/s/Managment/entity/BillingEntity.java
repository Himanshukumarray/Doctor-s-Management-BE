package com.Doctor.s.Doctor.s.Managment.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class BillingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ 1-to-1 mapping with AppointmentEntity
    @OneToOne
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private AppointmentEntity appointment;

    private Double amount;

    private String paymentMethod; // e.g., Cash, Card, UPI

    private String paymentStatus; // e.g., PENDING, PAID, FAILED

    private LocalDateTime billDate;

    private String description;

    // ✅ Constructor: auto set bill date
    @PrePersist
    public void onCreate() {
        this.billDate = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public AppointmentEntity getAppointment() { return appointment; }

    public void setAppointment(AppointmentEntity appointment) { this.appointment = appointment; }

    public Double getAmount() { return amount; }

    public void setAmount(Double amount) { this.amount = amount; }

    public String getPaymentMethod() { return paymentMethod; }

    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getPaymentStatus() { return paymentStatus; }

    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public LocalDateTime getBillDate() { return billDate; }

    public void setBillDate(LocalDateTime billDate) { this.billDate = billDate; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}
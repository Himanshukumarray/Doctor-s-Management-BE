package com.Doctor.s.Doctor.s.Managment.services;

import com.Doctor.s.Doctor.s.Managment.entity.AppointmentEntity;
import com.Doctor.s.Doctor.s.Managment.entity.BillingEntity;
import com.Doctor.s.Doctor.s.Managment.repository.AppointmentRepository;
import com.Doctor.s.Doctor.s.Managment.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingService {

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    // ✅ Create a new bill for an appointment
    public BillingEntity createBill(Long appointmentId, BillingEntity billing) {
        AppointmentEntity appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        billing.setAppointment(appointment);
        billing.setPaymentStatus("PENDING"); // default status
        return billingRepository.save(billing);
    }

    // ✅ Get all bills
    public List<BillingEntity> getAllBills() {
        return billingRepository.findAll();
    }

    // ✅ Get bill by ID
    public BillingEntity getBillById(Long id) {
        return billingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found"));
    }

    // ✅ Update payment status
    public BillingEntity updatePaymentStatus(Long id, String status) {
        BillingEntity bill = billingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found"));
        bill.setPaymentStatus(status);
        return billingRepository.save(bill);
    }

    // ✅ Delete bill
    public void deleteBill(Long id) {
        billingRepository.deleteById(id);
    }
}

package com.Doctor.s.Doctor.s.Managment.controller;

import com.Doctor.s.Doctor.s.Managment.entity.BillingEntity;
import com.Doctor.s.Doctor.s.Managment.services.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/billing")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @PostMapping("/create/{appointmentId}")
    public ResponseEntity<BillingEntity> createBill(
            @PathVariable Long appointmentId,
            @RequestBody BillingEntity billing) {
        return ResponseEntity.ok(billingService.createBill(appointmentId, billing));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BillingEntity>> getAllBills() {
        return ResponseEntity.ok(billingService.getAllBills());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillingEntity> getBillById(@PathVariable Long id) {
        return ResponseEntity.ok(billingService.getBillById(id));
    }

    @PutMapping("/update-status/{id}")
    public ResponseEntity<BillingEntity> updatePaymentStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(billingService.updatePaymentStatus(id, status));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBill(@PathVariable Long id) {
        billingService.deleteBill(id);
        return ResponseEntity.ok("Bill deleted successfully");
    }
}

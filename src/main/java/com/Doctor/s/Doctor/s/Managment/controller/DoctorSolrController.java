package com.Doctor.s.Doctor.s.Managment.controller;

import com.Doctor.s.Doctor.s.Managment.entity.DoctorSearch;
import com.Doctor.s.Doctor.s.Managment.services.DoctorSolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/solr/doctors")
public class DoctorSolrController {

    @Autowired
    private DoctorSolrService service;

    @PostMapping("/add")
    public ResponseEntity<String> addDoctor(@RequestBody DoctorSearch dto) {
        try {
            return ResponseEntity.ok(service.addDoctor(dto));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String keyword) {
        try {
            return ResponseEntity.ok(service.searchDoctors(keyword));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}


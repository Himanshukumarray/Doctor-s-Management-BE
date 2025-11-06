package com.Doctor.s.Doctor.s.Managment.controller;

import com.Doctor.s.Doctor.s.Managment.Entity.DoctorEntity;
import com.Doctor.s.Doctor.s.Managment.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/add")
    public DoctorEntity createDoctor(@RequestBody DoctorEntity doctor){
        return doctorService.createDoctor(doctor);
    }
}

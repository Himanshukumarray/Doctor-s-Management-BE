

package com.Doctor.s.Doctor.s.Managment.services;

import com.Doctor.s.Doctor.s.Managment.Entity.DoctorEntity;
import com.Doctor.s.Doctor.s.Managment.controller.DoctorController;
import com.Doctor.s.Doctor.s.Managment.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public DoctorEntity createDoctor(DoctorEntity doctor){
        System.out.println(doctor);
        return doctorRepository.save(doctor);
    }

    public List<DoctorEntity> getAll(){
        return doctorRepository.findAll();
    }

    public DoctorEntity getById(Long id){
        return doctorRepository.findById(id).orElse(null);
    }
}



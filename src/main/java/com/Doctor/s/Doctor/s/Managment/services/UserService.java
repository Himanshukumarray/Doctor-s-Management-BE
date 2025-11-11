

package com.Doctor.s.Doctor.s.Managment.services;

import com.Doctor.s.Doctor.s.Managment.entity.UserEntity;
import com.Doctor.s.Doctor.s.Managment.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private AuthRepository authRepository;

    public UserEntity createDoctor(UserEntity doctor){
        System.out.println("****************"+doctor+"************************");
        return authRepository.save(doctor);
    }

    public List<UserEntity> getAll(){
        return authRepository.findAll();
    }

    public UserEntity getById(Long id){
        return authRepository.findById(id).orElse(null);
    }
}



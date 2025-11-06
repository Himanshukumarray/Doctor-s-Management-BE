package com.Doctor.s.Doctor.s.Managment.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
public class DoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialty;
    private String email;
    private String phone;
    private String address;

    //Setter

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialty(String specialty){
        this.specialty= specialty;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public void setAddress(String address){
        this.address=address;
    }


    //Getter

    public String getName() {
        return name;
    }

    public String getSpecialty(){
        return specialty;
    }

    public String getEmail(){
        return email;
    }

    public String getPhone(){
        return phone;
    }

    public String getAddress(){
        return address;
    }

}

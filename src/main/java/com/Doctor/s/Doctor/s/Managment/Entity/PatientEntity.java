package com.Doctor.s.Doctor.s.Managment.Entity;

import jakarta.persistence.*;

@Entity
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String address;

    //Getter

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getAddress(){
        return address;
    }

    public String getPhone(){
        return phone;
    }

    //Setter
    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setAddress(String address){
        this.address=address;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }
}

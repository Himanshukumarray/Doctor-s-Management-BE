package com.Doctor.s.Doctor.s.Managment.entity;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;

public class DoctorSearch {

    @Id
    @Field
    private String id;

    @Field
    private String name;

    @Field
    private String specialization;

    // getters + setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public DoctorSearch(String id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public DoctorSearch() {
    }
}


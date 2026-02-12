package com.Doctor.s.Doctor.s.Managment.entity;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;

public class DoctorSearch {

    @Id
    @Field
    private String id;

    @Field
    private String name; // doctor name

    @Field
    private String specialization;

    @Field
    private String keywords; // new field to help synonym search

    // getters & setters

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getKeywords() { return keywords; }
    public void setKeywords(String keywords) { this.keywords = keywords; }

    public DoctorSearch() {}
}

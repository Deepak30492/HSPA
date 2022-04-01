package com.uhi.hsp.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.List;

@Data
@Table(schema = "hsp", name = "practitioner")
@Entity
@ToString
public class Practitioner implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "practitioner_id")
    private Integer practitionerId;
    @Column(name = "name")
    private String name;
    @Column(name = "gender")
    private char gender;
    @Column(name = "image")
    private String image;
    @Column(name = "cred")
    private String cred;
    @Column(name = "consultation_charge")
    private String consultationCharge;
    @Column(name = "currency")
    private String currency;
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "end_time")
    private String endTime;
    @Column(name = "status")
    private String status;

   @OneToOne(mappedBy = "practitionerId")
    private Fulfillments fulfillments;

}

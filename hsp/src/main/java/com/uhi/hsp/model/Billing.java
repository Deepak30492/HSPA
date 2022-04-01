package com.uhi.hsp.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(schema = "hsp")
@Data
@ToString
public class Billing implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bill_id")
    private Integer billingId;
    @Column(name = "state")
    private String state;

    @JoinColumn(name = "fulfillment_id")
    private Integer fulfillmentId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;

}

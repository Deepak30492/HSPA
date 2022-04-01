package com.uhi.hsp.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "hsp")
@Data
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private Integer addressId;
    @Column(name = "door")
    private String door;
    @Column(name = "building")
    private String building;
    @Column(name = "street")
    private String street;
    @Column(name = "area_code")
    private String areaCode;
    @Column(name = "billing_id")
    private Integer billing;
   
}

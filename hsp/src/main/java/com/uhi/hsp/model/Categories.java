package com.uhi.hsp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Data
@Table(schema = "hsp")
@Entity
@ToString
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Categories implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    @JsonBackReference
    private Integer categoryId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "provider_id", nullable = false)
    @JsonBackReference
    private Provider provider;

   // @OneToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    //private List<Fulfillments> fulfillments;


    @Column(name = "name")
    private String name;


}

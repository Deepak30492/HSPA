package com.uhi.hsp.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

//import com.dhp.sdk.beans.Start;

import lombok.Data;
import lombok.ToString;

@Data
@Table(schema = "hsp")
@Entity
@ToString
public class Fulfillments implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fulfillment_id")
	private Integer fulfillmentId;

	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name = "provider_id", nullable = false)
	private Provider provider;

	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name = "category_id", nullable = false)
	private Categories categories;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "practitioner_id")
	private Practitioner practitionerId;

	@Column(name = "start_time")
	private String startTime;
	@Column(name = "end_time")
	private String endTime;
	private String type;

}

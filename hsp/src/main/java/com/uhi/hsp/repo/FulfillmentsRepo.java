package com.uhi.hsp.repo;

import com.uhi.hsp.model.Fulfillments;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FulfillmentsRepo extends JpaRepository<Fulfillments, Integer> {
	public List<Fulfillments> findByFulfillmentIdAndProvider(Integer id,com.uhi.hsp.model.Provider provider);
}

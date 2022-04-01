package com.uhi.hsp.repo;

import com.uhi.hsp.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepo extends JpaRepository<Billing, Integer> {
}

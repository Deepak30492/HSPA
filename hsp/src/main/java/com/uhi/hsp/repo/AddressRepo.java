package com.uhi.hsp.repo;

import com.uhi.hsp.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Integer> {
}

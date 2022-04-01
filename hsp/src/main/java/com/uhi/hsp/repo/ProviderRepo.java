package com.uhi.hsp.repo;

import com.uhi.hsp.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ProviderRepo extends JpaRepository<Provider, Integer> {
}

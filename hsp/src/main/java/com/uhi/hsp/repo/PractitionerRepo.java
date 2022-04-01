package com.uhi.hsp.repo;

import com.uhi.hsp.model.Practitioner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PractitionerRepo extends JpaRepository<Practitioner, Integer> {
}

package com.uhi.hsp.repo;

import com.uhi.hsp.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepo extends JpaRepository<Categories, Integer> {
}

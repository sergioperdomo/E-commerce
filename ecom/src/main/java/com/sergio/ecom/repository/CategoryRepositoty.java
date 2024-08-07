package com.sergio.ecom.repository;

import com.sergio.ecom.entity.Category;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositoty extends JpaRepository<Category, Long> {





}

package com.bms.business_management_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bms.business_management_system.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}

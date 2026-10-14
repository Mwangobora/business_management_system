package com.bms.business_management_system.repositories;

import com.bms.business_management_system.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    
}

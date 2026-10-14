package com.bms.business_management_system;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bms.business_management_system.models.Product;
import com.bms.business_management_system.repositories.ProductRepository;

@Service 
public class ProductService {
    
    private  final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public  List<Product> getAllProducts()
    {
        return  productRepository.findAll();
    }
}

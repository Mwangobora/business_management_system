package com.bms.business_management_system.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bms.business_management_system.ProductService;
import com.bms.business_management_system.models.Product;

    @RestController 
    @RequestMapping("/api")
    public class ProductController {

        private final  ProductService productService;

        public ProductController(ProductService productService) {
            this.productService = productService;
        }
        
        @GetMapping("/products")
        public List<Product> getAllProducts()
        {
        return  productService.getAllProducts();
        }

        @PostMapping("/products")
        public ResponseEntity<Product> createProduct(@RequestBody  Product product)
        {
            Product productSaved = productService.createProduct(product);
            return  ResponseEntity.status(HttpStatus.CREATED).body(productSaved);
        }
        
        
    }

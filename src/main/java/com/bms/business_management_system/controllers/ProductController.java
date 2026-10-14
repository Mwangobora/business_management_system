package com.bms.business_management_system.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bms.business_management_system.services.ProductService;
import com.bms.business_management_system.models.Product;

    @RestController 
    @RequestMapping("/api")
    public class ProductController {

        private final ProductService productService_1;
        private final  ProductService productService;

        public ProductController(ProductService productService, ProductService productService_1) {
            this.productService = productService;
            this.productService_1 = productService_1;
        }

        @GetMapping("/products/{id}")
        public ResponseEntity<Product> getProductById(@PathVariable Long id)
        {
           Optional<Product> product = productService.getProductById(id);

           if(product.isPresent())
             return ResponseEntity.ok(product.get());
         
           else
             return ResponseEntity.notFound().build();
           
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

        @PutMapping("/products/{id}")
        public ResponseEntity<Product> updateProduct(@PathVariable Long id, 
                                                     @RequestBody Product updatedProduct)
        {
           Optional<Product> product = productService.updateProduct(id, updatedProduct);

           if(product.isPresent()){
            return ResponseEntity.ok().body(product.get());
           }
           else{    
            return  ResponseEntity.notFound().build();
           }
        }

        @DeleteMapping("/products/{id}")
        public  ResponseEntity<Void> deleteProduct(@PathVariable Long id)
        {
            if(productService.deleteProduct(id))
            {
                return  ResponseEntity.noContent().build();
            }

            return ResponseEntity.notFound().build();
        }
        
        
    }

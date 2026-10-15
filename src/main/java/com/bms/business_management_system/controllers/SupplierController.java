package com.bms.business_management_system.controllers;

import com.bms.business_management_system.models.Supplier;
import com.bms.business_management_system.services.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SupplierController {
    private final SupplierService supplierService;

    public  SupplierController(SupplierService supplierService){
        this.supplierService = supplierService;
    }

    @GetMapping("/suppliers")
    public List<Supplier> getAllSupplier()
    {
        return  supplierService.getAllSupplier();
    }

    @GetMapping("/supplier/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id)
    {
        Optional<Supplier> supplier = supplierService.getSupplierById(id);

        if(supplier.isPresent())
            ResponseEntity.ok().body(supplier);
        return  ResponseEntity.notFound().build();
    }
    
}

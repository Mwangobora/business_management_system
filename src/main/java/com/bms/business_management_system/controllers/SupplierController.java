package com.bms.business_management_system.controllers;

import com.bms.business_management_system.models.Supplier;
import com.bms.business_management_system.services.SupplierService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/suppliers/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id)
    {
        Optional<Supplier> supplier = supplierService.getSupplierById(id);

        if(supplier.isPresent())
            return  ResponseEntity.ok(supplier.get());
        return  ResponseEntity.notFound().build();
    }

    @PostMapping("/suppliers")
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier)
    {
        Supplier savedSupplier = supplierService.createSupplier(supplier);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSupplier);
    }

    @PutMapping("/suppliers/{id}")
    public Optional<Supplier> updateSupplier(@PathVariable Long id,
                                             @RequestBody Supplier updatedSupplier)
    {
        Optional<Supplier> supplier = supplierService.updateSupplier(id,updatedSupplier);
        if(supplier.isEmpty())
            return Optional.empty();
        return ResponseEntity.ok().body(supplier).getBody();

    }

    @DeleteMapping("/suppliers/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable Long id)
    {
        if(supplierService.deleteSupplier(id))
           return ResponseEntity.ok().body("Deleted successifully");
        return ResponseEntity.notFound().build();
    }


}

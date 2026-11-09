package com.bms.business_management_system.controllers;

import com.bms.business_management_system.dto.supplier.SupplierRequests;
import com.bms.business_management_system.dto.supplier.SupplierResponse;
import com.bms.business_management_system.models.Supplier;
import com.bms.business_management_system.services.SupplierService;

import jakarta.validation.Valid;
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
    public  ResponseEntity<List<SupplierResponse>> getAllSupplier()
    {
        List<SupplierResponse> suppliers = supplierService.getAllSupplier();
        return ResponseEntity.ok().body(suppliers);
    }

    @GetMapping("/suppliers/{id}")
    public ResponseEntity<SupplierResponse> getSupplierById(@PathVariable Long id)
    {
        Optional<SupplierResponse> supplier = supplierService.getSupplierById(id);

        if(supplier.isPresent())
            return  ResponseEntity.ok(supplier.get());
        return  ResponseEntity.notFound().build();
    }

    @PostMapping("/suppliers")
    public ResponseEntity<SupplierResponse> createSupplier(@RequestBody @Valid SupplierRequests requests)
    {
        SupplierResponse savedSupplier = supplierService.createSupplier(requests);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSupplier);
    }

    @PutMapping("/suppliers/{id}")
    public Optional<SupplierResponse> updateSupplier(@PathVariable Long id,
                                             @RequestBody Supplier updatedSupplier)
    {
        Optional<SupplierResponse> supplier = supplierService.updateSupplier(id,updatedSupplier);
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

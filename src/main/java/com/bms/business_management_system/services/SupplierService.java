package com.bms.business_management_system.services;

import com.bms.business_management_system.models.Supplier;
import com.bms.business_management_system.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    private  final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository)
    {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getAllSupplier()
    {
        return  supplierRepository.findAll();
    }

    public Optional<Supplier> getSupplierById(Long id)
    {
        return supplierRepository.findById(id);
    }

    public Supplier createSupplier(Supplier supplier)
    {
        return supplierRepository.save(supplier);
    }
    public  Optional<Supplier> updateSupplier(Long id, Supplier updatedSupplier)
    {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if(supplier.isEmpty())
            return Optional.empty();
        Supplier existingSupplier = supplier.get();
        existingSupplier.setName(updatedSupplier.getName());
        existingSupplier.setEmail(updatedSupplier.getEmail());
        existingSupplier.setPhoneNumber(updatedSupplier.getPhoneNumber());
        existingSupplier.setAddress(updatedSupplier.getAddress());

        Supplier savedSupplier = supplierRepository.save(existingSupplier);
        return Optional.of(savedSupplier);
    }

    public boolean deleteSupplier(Long id)
    {
        if(supplierRepository.existsById(id))
        {
            supplierRepository.deleteById(id);
            return  true;
        }
        return false;
    }
}

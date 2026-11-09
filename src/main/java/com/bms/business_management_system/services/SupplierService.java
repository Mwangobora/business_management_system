package com.bms.business_management_system.services;

import com.bms.business_management_system.dto.supplier.SupplierRequests;
import com.bms.business_management_system.dto.supplier.SupplierResponse;
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

    public SupplierResponse toResponse(Supplier supplier){
        SupplierResponse supplierResponse = new SupplierResponse();

        supplierResponse.setId(supplier.getId());
        supplierResponse.setName(supplier.getName());
        supplierResponse.setEmail(supplier.getEmail());
        supplierResponse.setPhoneNumber(supplier.getPhoneNumber());
        supplierResponse.setAddress(supplier.getAddress());

        return supplierResponse;

    }

    public List<SupplierResponse> getAllSupplier()
    {
        return  supplierRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<SupplierResponse> getSupplierById(Long id)
    {
        return supplierRepository.findById(id).map(this::toResponse);
    }

    public SupplierResponse createSupplier(SupplierRequests requests)
    {
        Supplier supplier = new Supplier();
        supplier.setName(requests.getName());
        supplier.setPhoneNumber(requests.getPhoneNumber());
        supplier.setEmail(requests.getEmail());
        supplier.setAddress(requests.getAddress());

        Supplier savedSupplier = supplierRepository.save(supplier);
        return toResponse(savedSupplier);
    }
    public  Optional<SupplierResponse> updateSupplier(Long id, Supplier updatedSupplier)
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
        return Optional.of(toResponse(savedSupplier));
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

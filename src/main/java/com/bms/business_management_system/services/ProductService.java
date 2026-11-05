package com.bms.business_management_system.services;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bms.business_management_system.dto.ProductRequests;
import com.bms.business_management_system.dto.ProductResponse;
import com.bms.business_management_system.models.Product;
import com.bms.business_management_system.repositories.ProductRepository;

@Service 
public class ProductService {
    
    private  final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse toResponse(Product product)
    {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());

        return  response;
    }

    public  List<ProductResponse> getAllProducts()
    {
        return  productRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }


    public ProductResponse createProduct(ProductRequests request)
    {
        Product product = new Product();
        product.setName(request.getName()); 
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        Product savedProduct = productRepository.save(product);
        return toResponse(savedProduct);
    }

    public Optional<ProductResponse> getProductById(Long id)
    {

        return  productRepository.findById(id)
                .map(this::toResponse);
    }

    public Optional<ProductResponse> updateProduct(Long id, Product request)
    {
       Optional<Product> product = productRepository.findById(id);

       
       if(product.isEmpty())
          return  Optional.empty();
        
        Product existingProduct = product.get();

        existingProduct.setName(request.getName());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setQuantity(request.getQuantity());

        Product savedProduct = productRepository.save(existingProduct);

        return Optional.of(toResponse(savedProduct));
        
    }

    public boolean deleteProduct(Long id)
    {
        if(productRepository.existsById(id))
        {
            productRepository.deleteById(id);
            return true;
        }

        return  false;
    }
}

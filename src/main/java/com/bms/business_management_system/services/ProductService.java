package com.bms.business_management_system.services;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bms.business_management_system.dto.ProductRequests;
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

    public Product createProduct(Product product)
    {
        return productRepository.save(product);
    }

    public Product createProduct(ProductRequests request)
    {
        Product product = new Product();
        product.setName(request.getName()); 
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        return productRepository.save(product);
    }

    public Optional<Product> getProductById(Long id)
    {
        return  productRepository.findById(id);
    }

    public Optional<Product> updateProduct(Long id, Product updatedProduct)
    {
       Optional<Product> product = productRepository.findById(id);

       
       if(product.isEmpty())
          return  Optional.empty();
        
        Product existingProduct = product.get();

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setQuantity(updatedProduct.getQuantity());

        Product savedProduct = productRepository.save(existingProduct);

        return Optional.of(savedProduct);
        
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

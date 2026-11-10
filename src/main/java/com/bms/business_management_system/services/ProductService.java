package com.bms.business_management_system.services;
import java.util.List;
import java.util.Optional;

import com.bms.business_management_system.models.Category;
import com.bms.business_management_system.repositories.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bms.business_management_system.dto.products.ProductRequests;
import com.bms.business_management_system.dto.products.ProductResponse;
import com.bms.business_management_system.models.Product;
import com.bms.business_management_system.repositories.ProductRepository;
import org.springframework.web.server.ResponseStatusException;

@Service 
public class ProductService {
    
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;



    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductResponse toResponse(Product product)
    {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());

        if(product.getCategory() != null){
            response.setCategoryId(product.getCategory().getId());
        }

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
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Category not found"));
        Product product = new Product();
        product.setName(request.getName()); 
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);

        return toResponse(savedProduct);
    }

    public Optional<ProductResponse> getProductById(Long id)
    {

        return  productRepository.findById(id)
                .map(this::toResponse);
    }

    public Optional<ProductResponse> updateProduct(Long id, ProductRequests request)
    {
       Optional<Product> product = productRepository.findById(id);

       if(product.isEmpty())
          return  Optional.empty();

       Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Category not found"));
        
        Product existingProduct = product.get();
        existingProduct.setName(request.getName());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setQuantity(request.getQuantity());
        existingProduct.setCategory(category);

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

package com.bms.business_management_system.services;

import java.util.List;
import java.util.Optional;

import com.bms.business_management_system.models.Category;
import com.bms.business_management_system.models.Product;
import com.bms.business_management_system.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories()
    {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id)
    {
        return  categoryRepository.findById(id);
    }

    public Category createCategory(Category category)
    {
        return categoryRepository.save(category);
    }
    
}

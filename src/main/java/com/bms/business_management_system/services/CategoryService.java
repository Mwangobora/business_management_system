package com.bms.business_management_system.services;

import java.util.List;
import java.util.Optional;

import com.bms.business_management_system.models.Category;
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

    public Optional<Category> updateCategory(Long id, Category updatedCategories)
    {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty())
            return Optional.empty();

        Category existingCategory = category.get();
        existingCategory.setName(updatedCategories.getName());
        existingCategory.setDescription(updatedCategories.getDescription());

        Category savedCategory = categoryRepository.save(existingCategory);
        return  Optional.of(savedCategory);
    }

    public boolean deleteCategory(Long id)
    {
        if(categoryRepository.existsById(id))
        {
            categoryRepository.deleteById(id);  
        }

        return false;

    }
    
    
}

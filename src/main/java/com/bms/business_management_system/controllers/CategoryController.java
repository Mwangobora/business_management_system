package com.bms.business_management_system.controllers;

import com.bms.business_management_system.models.Category;
import com.bms.business_management_system.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    public  CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories()
    {
        return  categoryService.getAllCategories();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id)
    {
        Optional<Category> category = categoryService.getCategoryById(id);
        if(category.isPresent())
            return  ResponseEntity.ok(category.get());
        else
            return  ResponseEntity.notFound().build();
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category)
    {
        Category categorySave = categoryService.createCategory(category);
        return  ResponseEntity.status(HttpStatus.CREATED).body(categorySave);
    }

    @PutMapping("/categories/{id}")
    public  ResponseEntity<Category> updateCategory(@PathVariable Long id,
                                                    @RequestBody Category updatedCategories)
    {
        Optional<Category> category = categoryService.updateCategory(id,updatedCategories);
        if(category.isPresent())
            return  ResponseEntity.ok().body(category.get());
        else
         return ResponseEntity.notFound().build();
    }

    @DeleteMapping("categories/{id}")
    public  ResponseEntity<Void> deleteCategory(@PathVariable Long id)
    {
        if(categoryService.deleteCategory(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

}

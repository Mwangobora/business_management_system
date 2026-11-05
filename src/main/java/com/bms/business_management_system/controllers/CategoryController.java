package com.bms.business_management_system.controllers;

import com.bms.business_management_system.dto.categories.CategoryRequests;
import com.bms.business_management_system.dto.categories.CategoryResponse;
import com.bms.business_management_system.models.Category;
import com.bms.business_management_system.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class  CategoryController {

    private final CategoryService categoryService;

    public  CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> getAllCategories()
    {
        List<CategoryResponse> category = categoryService.getAllCategories();
        return  ResponseEntity.ok().body(category);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id)
    {
        Optional<CategoryResponse> category = categoryService.getCategoryById(id);
        if(category.isPresent())
            return  ResponseEntity.ok(category.get());
        else
            return  ResponseEntity.notFound().build();
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryResponse> createCategory(@Valid  @RequestBody CategoryRequests requests)
    {
        CategoryResponse categorySave = categoryService.createCategory(requests);
        return  ResponseEntity.status(HttpStatus.CREATED).body(categorySave);
    }

    @PutMapping("/categories/{id}")
    public  ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id,
                                                    @RequestBody Category updatedCategories)
    {
        Optional<CategoryResponse> category = categoryService.updateCategory(id,updatedCategories);
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

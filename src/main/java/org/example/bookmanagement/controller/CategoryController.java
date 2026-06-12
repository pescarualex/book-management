package org.example.bookmanagement.controller;

import jakarta.validation.Valid;
import org.example.bookmanagement.dto.CategoryRequest;
import org.example.bookmanagement.dto.CategoryResponse;
import org.example.bookmanagement.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Void> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        categoryService.createCategory(categoryRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable long id) {
        CategoryResponse categoryById = categoryService.getCategoryById(id);

        return ResponseEntity.ok(categoryById);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> allCategories = categoryService.getAllCategories();

        return ResponseEntity.ok(allCategories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable long id, @Valid @RequestBody CategoryRequest categoryRequest) {
        categoryService.updateCategory(id, categoryRequest);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable long id) {
        categoryService.deleteCategory(id);

        return ResponseEntity.noContent().build();
    }


}

package org.example.bookmanagement.service;

import org.example.bookmanagement.dto.CategoryRequest;
import org.example.bookmanagement.dto.CategoryResponse;
import org.example.bookmanagement.entity.Category;
import org.example.bookmanagement.exception.CategoryNotFoundException;
import org.example.bookmanagement.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void createCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setCategoryName(categoryRequest.categoryName());

        categoryRepository.save(category);
    }

    public CategoryResponse getCategoryById(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException(id)
        );

        return new CategoryResponse(category.getId(), category.getCategoryName());
    }

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(c -> new CategoryResponse(
                        c.getId(),
                        c.getCategoryName()))
                .toList();
    }

    public void updateCategory(long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException(id)
        );

        category.setCategoryName(categoryRequest.categoryName());

        categoryRepository.save(category);
    }

    public void deleteCategory(long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException(id);
        }

        categoryRepository.deleteById(id);
    }
}

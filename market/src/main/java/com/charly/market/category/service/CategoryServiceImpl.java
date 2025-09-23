package com.charly.market.category.service;

import com.charly.market.category.model.dto.CategoryResponse;
import com.charly.market.category.model.dto.CreateCategoryRequest;
import com.charly.market.category.model.entity.Category;
import com.charly.market.category.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void createCategory(CreateCategoryRequest request) {
        Category category = Category.builder()
                .type(request.type())
                .categoryName(request.categoryName())
                .status(request.status())
                .build();

        categoryRepository.save(category);
    }

    @Override
    public List<CategoryResponse> findAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        for (Category category : categories) {
            CategoryResponse categoryResponse = new CategoryResponse(
                    category.getType(),
                    category.getCategoryName(),
                    category.getStatus()
            );
            categoryResponseList.add(categoryResponse);
        }
        return categoryResponseList;
    }

    @Override
    public CategoryResponse findById(Long id) {
        return categoryRepository.findById(id)
                .map(category -> new CategoryResponse(
                        category.getType(),
                        category.getCategoryName(),
                        category.getStatus()))
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
    }


    @Transactional
    @Override
    public void delete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
        category.deactivatedCategoryStatus();
    }
}

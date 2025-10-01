package com.charly.market.category.service.util;


import com.charly.market.category.model.entity.Category;
import com.charly.market.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryFinder {

    private final CategoryRepository categoryRepository;

    public Category getById(Long id){return categoryRepository.findById(id).orElseThrow();}
}

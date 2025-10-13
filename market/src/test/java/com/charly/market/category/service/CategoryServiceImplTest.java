package com.charly.market.category.service;

import com.charly.market.category.model.dto.CreateCategoryRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void createCategory() {
        for (long i = 1; i < 6; i++) {
            CreateCategoryRequest createCategoryRequest =
                    new CreateCategoryRequest("타입" + i , "카테고리 이름" + i, "Y");
            categoryService.createCategory(createCategoryRequest);
        }
    }
}

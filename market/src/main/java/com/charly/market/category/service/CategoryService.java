package com.charly.market.category.service;

import com.charly.market.category.model.dto.CategoryResponse;
import com.charly.market.category.model.dto.CreateCategoryRequest;
import com.charly.market.category.repository.CategoryRepository;

import java.util.List;

public interface CategoryService {
    //카테고리 등록
    void createCategory(CreateCategoryRequest request);


    //카테고리 조회
    List<CategoryResponse> findAll();


    //카테고리 검색
    CategoryResponse findById(Long id);


    //카테고리 삭제
    void delete(Long id);
}

package com.charly.market.category.controller;

import com.charly.market.category.model.dto.CategoryResponse;
import com.charly.market.category.model.dto.CreateCategoryRequest;
import com.charly.market.category.repository.CategoryRepository;
import com.charly.market.category.service.CategoryService;
import com.charly.market.inquiry.model.dto.InquiryResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    //등록
    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CreateCategoryRequest request){
        System.out.println(request.toString());
        categoryService.createCategory(request);
        return ResponseEntity.ok("카테고리");
    }

    //조회
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findCategoryList() {
        List<CategoryResponse> categoryListResponses = categoryService.findAll();
        return ResponseEntity.ok(categoryListResponses);
    }

    //검색
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse>  findCategoryById(@PathVariable @NotNull Long id){
        CategoryResponse categoryResponse = categoryService.findById(id);
        return ResponseEntity.ok(categoryResponse);
    }

    //삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCatgory(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.ok("삭제 성공");
    }
}

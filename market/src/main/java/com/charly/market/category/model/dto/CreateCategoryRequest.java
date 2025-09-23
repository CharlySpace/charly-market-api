package com.charly.market.category.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest (
        @NotBlank String type,
        @NotBlank String categoryName,
        @NotBlank String status
){
}

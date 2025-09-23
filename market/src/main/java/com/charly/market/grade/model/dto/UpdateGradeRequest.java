package com.charly.market.grade.model.dto;

public record UpdateGradeRequest(
    Long id,
    String name,
    int feeRate,
    int standard
) {

}

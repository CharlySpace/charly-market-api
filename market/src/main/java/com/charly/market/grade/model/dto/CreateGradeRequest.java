package com.charly.market.grade.model.dto;

public record CreateGradeRequest(
    String name,
    int feeRate,
    int standard
) {

}

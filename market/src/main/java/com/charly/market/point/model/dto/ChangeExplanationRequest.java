package com.charly.market.point.model.dto;

public record ChangeExplanationRequest(
    //String nowExplanation
    Long id,//나중에 바꾸기
    String newExplanation//나중에 NotBlank필요
) {

}

package com.charly.market.inquiry.controller;

import com.charly.market.admin.log.model.dto.PageResponse;
import com.charly.market.inquiry.model.dto.AnswerInquiryRequest;
import com.charly.market.inquiry.model.dto.CreateInquiryRequest;
import com.charly.market.inquiry.model.dto.InquiryResponse;
import com.charly.market.inquiry.model.dto.InquirySearchRequest;
import com.charly.market.inquiry.repository.InquiryRepository;
import com.charly.market.inquiry.service.InquiryService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/inquiry")
public class InquiryController {

    private final InquiryService inquiryService;
    private final InquiryRepository inquiryRepository;

    //조회
    @GetMapping
    public ResponseEntity<List<InquiryResponse>> findInquiryList() {
        List<InquiryResponse> inquiryListResponses = inquiryService.findAll();
        return ResponseEntity.ok(inquiryListResponses);
    }

    //등록
    //fk처리 안됨
    @PostMapping
    public ResponseEntity<String> createInquiry(@RequestBody CreateInquiryRequest request){
        System.out.println(request.toString());
        inquiryService.createInquiry(request);
        return ResponseEntity.ok("문의사항");
    }

    //검색
    @GetMapping("/{id}")
    public ResponseEntity<InquiryResponse>  findInquiryById(@PathVariable @NotNull Long id){
        InquiryResponse inquiryResponse = inquiryService.findById(id);
        return ResponseEntity.ok(inquiryResponse);
    }

    //문의 답변
    @PatchMapping("/{id}/answer")
    public ResponseEntity<String> answerInquiry(
            @PathVariable Long id,
            @RequestBody AnswerInquiryRequest request
    ) {
        inquiryService.answerInquiry(id, request.adminId(), request.answer());
        return ResponseEntity.ok("답변 완료 처리 성공");
    }

    // 페이징
    @GetMapping("/search")
    public PageResponse<InquiryResponse> InquarySearchList(InquirySearchRequest request) {
        return PageResponse.of(inquiryService.inquirySearch(request));
    }


}

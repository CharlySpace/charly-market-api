package com.charly.market.inquiry.controller;

import com.charly.market.inquiry.model.dto.CreateInquiryRequest;
import com.charly.market.inquiry.model.dto.InquiryResponse;
import com.charly.market.inquiry.model.entity.Inquiry;
import com.charly.market.inquiry.repository.InquiryRepository;
import com.charly.market.inquiry.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/Inquiry")
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
    @GetMapping("/{inquiryId}")
    public ResponseEntity<InquiryResponse>  findInquiryById(@PathVariable Long inquiryId){
        InquiryResponse inquiryResponse = inquiryService.findByInquiryId(inquiryId);
        return ResponseEntity.ok(inquiryResponse);
    }

    //삭제
    @DeleteMapping("/{inquiryId}")
    public ResponseEntity<String> deleteInquiry(@PathVariable Long inquiryId){
        inquiryService.delete(inquiryId);
        return ResponseEntity.ok("삭제 성공");
    }


}

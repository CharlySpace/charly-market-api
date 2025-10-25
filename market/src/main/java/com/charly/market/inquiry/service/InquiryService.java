package com.charly.market.inquiry.service;

import com.charly.market.inquiry.model.dto.CreateInquiryRequest;
import com.charly.market.inquiry.model.dto.InquiryResponse;
import com.charly.market.inquiry.model.dto.InquirySearchRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InquiryService {
    //문의사항 등록
    void createInquiry(CreateInquiryRequest request);

    //문의사항 조회
    List<InquiryResponse> findAll();

    //문의사항 검색
    InquiryResponse findById(Long id);

    //문의사항 답변
    void answerInquiry(Long id, Long adminId, String answer);

    // 페이징
    Page<InquiryResponse> inquirySearch(InquirySearchRequest request);

}

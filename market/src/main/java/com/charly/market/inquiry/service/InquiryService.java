package com.charly.market.inquiry.service;

import com.charly.market.inquiry.model.dto.CreateInquiryRequest;
import com.charly.market.inquiry.model.dto.InquiryResponse;

import java.util.List;

public interface InquiryService {
    //문의사항 등록
    void createInquiry(CreateInquiryRequest request);

    //문의사항 조회
    List<InquiryResponse> findAll();

    //문의사항 검색
    InquiryResponse findByInquiryId(Long inquiryId);

    //문의사항 삭제
    void delete(Long inquiryId);
}

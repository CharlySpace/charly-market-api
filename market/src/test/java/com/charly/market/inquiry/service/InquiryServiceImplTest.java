package com.charly.market.inquiry.service;

import com.charly.market.inquiry.model.dto.CreateInquiryRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InquiryServiceImplTest {

    @Autowired
    private InquiryService inquiryService;

    @Test
    void createInquiry() {
        for(long i = 1; i < 6 ; i++) {

            CreateInquiryRequest createInquiryRequest = new CreateInquiryRequest("제목" + i, "내용" + i, 'N', "답변" + i , i );

            inquiryService.createInquiry(createInquiryRequest);
        }
    }
}
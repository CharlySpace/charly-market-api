package com.charly.market.inquiry.service;

import com.charly.market.inquiry.model.dto.CreateInquiryRequest;
import com.charly.market.inquiry.model.dto.InquiryResponse;
import com.charly.market.inquiry.model.entity.Inquiry;
import com.charly.market.inquiry.repository.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;

    // 문의 create
    @Override
    public void createInquiry(CreateInquiryRequest request) {
        Inquiry inquiry = Inquiry.builder()
                .inquiryTitle(request.inquiryTitle())
                .inquiryContent(request.inquiryContent())
                .inquiryStatus(request.inquiryStatus())
                .inquiryAnswer(request.inquiryAnswer())
                .build();

        inquiryRepository.save(inquiry);
    }

    //조회
    @Override
    public List<InquiryResponse> findAll() {
        List<Inquiry> inquiries = inquiryRepository.findAll();
        List<InquiryResponse> inquiryResponseList = new ArrayList<>();
        for (Inquiry inquiry : inquiries) {
            InquiryResponse inquiryResponse = new InquiryResponse(
                    inquiry.getInquiryTitle(),
                    inquiry.getInquiryContent(),
                    inquiry.getInquiryStatus(),
                    inquiry.getInquiryAnswer()
            );
            inquiryResponseList.add(inquiryResponse);
        }
        return inquiryResponseList;
    }

    //검색
    @Override
    public InquiryResponse findByInquiryId(Long inquiryId) {
        Inquiry inquiry = inquiryRepository.findByInquiryId(inquiryId);
        return new InquiryResponse(
                inquiry.getInquiryTitle(),
                inquiry.getInquiryContent(),
                inquiry.getInquiryStatus(),
                inquiry.getInquiryAnswer()
        );
    }

    // 삭제
    @Override
    public void delete(Long inquiryId) {
        Inquiry inquiry = inquiryRepository.findByInquiryId(inquiryId);
        inquiry.deactivatedInquiryStatus();
    }
}

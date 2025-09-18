package com.charly.market.inquiry.service;

import com.charly.market.inquiry.model.dto.CreateInquiryRequest;
import com.charly.market.inquiry.model.dto.InquiryResponse;
import com.charly.market.inquiry.model.entity.Inquiry;
import com.charly.market.inquiry.repository.InquiryRepository;
import jakarta.persistence.EntityNotFoundException;
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
                .title(request.title())
                .content(request.content())
                .status(request.status())
                .answer(request.answer())
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
                    inquiry.getTitle(),
                    inquiry.getAnswer(),
                    inquiry.getStatus(),
                    inquiry.getAnswer()
            );
            inquiryResponseList.add(inquiryResponse);
        }
        return inquiryResponseList;
    }

    //검색
    @Override
    public InquiryResponse findById(Long id) {
        return inquiryRepository.findById(id)
                .map(inquiry -> new InquiryResponse(
                        inquiry.getTitle(),
                        inquiry.getContent(),
                        inquiry.getStatus(),
                        inquiry.getAnswer()))
                .orElseThrow(() -> new EntityNotFoundException("Inquiry not found with id: " + id));
    }

    // 삭제
    @Transactional
    @Override
    public void delete(Long id) {
        Inquiry inquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inquiry not found with id: " + id));
        inquiry.deactivatedInquiryStatus();
    }
}

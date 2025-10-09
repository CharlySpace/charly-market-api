package com.charly.market.inquiry.service;

import com.charly.market.inquiry.model.dto.CreateInquiryRequest;
import com.charly.market.inquiry.model.dto.InquiryResponse;
import com.charly.market.inquiry.model.entity.Inquiry;
import com.charly.market.inquiry.repository.InquiryRepository;
import com.charly.market.user.model.entity.User;
import com.charly.market.user.service.util.UserFinder;
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
    private final UserFinder userFinder;

    // 문의 create
    // admin_id 아직 반영 안함
    @Override
    public void createInquiry(CreateInquiryRequest request) {
        Inquiry inquiry = Inquiry.builder()
                .title(request.title())
                .content(request.content())
                .status(request.status())
                .answer(request.answer())
                .user(userFinder.getById(request.seller()))
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
                    inquiry.getUser().getId(),
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
                        inquiry.getUser().getId(),
                        inquiry.getTitle(),
                        inquiry.getContent(),
                        inquiry.getStatus(),
                        inquiry.getAnswer()))
                .orElseThrow(() -> new EntityNotFoundException("Inquiry not found with id: " + id));
    }

    // 문의 답변 완료 상태로 만들기 "Y" -> "N"
    @Transactional
    @Override
    public void answerInquiry(Long inquiryId, Long adminId, String answer) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new EntityNotFoundException("Inquiry not found with id: " + inquiryId));

        User admin = userFinder.getById(adminId); // admin User 조회
        inquiry.answerInquiry(admin, answer);         // 엔티티 메서드로 상태 + 답변 + 관리자 반영
    }


}

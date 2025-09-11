package com.charly.market.notice.service;

import com.charly.market.notice.model.dto.CreateNoticeRequest;
import com.charly.market.notice.model.dto.NoticeResponse;
import com.charly.market.notice.model.entity.Notice;
import com.charly.market.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public void createNotice(CreateNoticeRequest request) {
        Notice notice = Notice.builder()
                .noticeTitle(request.noticeTitle())
                .noticeContent(request.noticeContent())
                .noticeStatus(request.noticeStatus())
                .build();

        noticeRepository.save(notice);
    }

    @Override
    public List<NoticeResponse> findAll() {
        List<Notice> notices = noticeRepository.findAll();
        List<NoticeResponse> noticeResponseList = new ArrayList<>();
        for (Notice notice : notices) {
            NoticeResponse noticeResponse = new NoticeResponse(
                    notice.getNoticeTitle(),
                    notice.getNoticeContent(),
                    notice.getNoticeStatus()
            );
            noticeResponseList.add(noticeResponse);
        }
        return noticeResponseList;
    }

    @Override
    public NoticeResponse findByNoticeId(Long noticeId) {
        Notice notice = noticeRepository.findByNoticeId(noticeId);
        return new NoticeResponse(
                notice.getNoticeTitle(),
                notice.getNoticeContent(),
                notice.getNoticeStatus()
        );
    }
    @Transactional
    @Override
    public void delete(Long noticeId) {
        Notice notice = noticeRepository.findByNoticeId(noticeId);
        notice.deactivatedNoticeStatus();

    }
}

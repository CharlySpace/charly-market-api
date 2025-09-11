package com.charly.market.notice.service;

import com.charly.market.notice.model.dto.CreateNoticeRequest;
import com.charly.market.notice.model.dto.NoticeListResponse;
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
                .notice_title(request.notice_title())
                .notice_content(request.notice_content())
                .notice_status(request.notice_status())
                .build();

        noticeRepository.save(notice);
    }

    @Override
    public List<NoticeListResponse> findAll() {
        List<Notice> notices = noticeRepository.findAll();
        List<NoticeListResponse> noticeResponseList = new ArrayList<>();
        for (Notice notice : notices) {
            NoticeListResponse noticeListResponse = new NoticeListResponse(
                    notice.getNotice_title(),
                    notice.getNotice_content(),
                    notice.getNotice_status()
            );
            noticeResponseList.add(noticeListResponse);
        }
        return noticeResponseList;
    }
}

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
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public void createNotice(CreateNoticeRequest request) {
        Notice notice = Notice.builder()
                .title(request.title())
                .content(request.content())
                .status(request.status())
                .build();

        noticeRepository.save(notice);
    }

    @Override
    public List<NoticeResponse> findAll() {
        List<Notice> notices = noticeRepository.findAll();
        List<NoticeResponse> noticeResponseList = new ArrayList<>();
        for (Notice notice : notices) {
            NoticeResponse noticeResponse = new NoticeResponse(
                    notice.getTitle(),
                    notice.getContent(),
                    notice.getStatus()
            );
            noticeResponseList.add(noticeResponse);
        }
        return noticeResponseList;
    }

    @Override
    public NoticeResponse findById(Long id) {
        Optional<Notice> notice = noticeRepository.findById(id);

        return notice.map(value -> new NoticeResponse(value.getTitle(),
                value.getContent(),
                value.getStatus())).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Optional<Notice> notice = noticeRepository.findById(id);
        notice.ifPresent(noticeRepository::delete);
    }
}

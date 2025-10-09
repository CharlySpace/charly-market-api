package com.charly.market.notice.service.util;

import com.charly.market.notice.model.entity.Notice;
import com.charly.market.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoticeFinder {
    private final NoticeRepository noticeRepository;

    public Notice getById(Long id) {
        return noticeRepository.findById(id).orElseThrow();
    }
}

package com.charly.market.notice.service;

import com.charly.market.notice.model.dto.CreateNoticeRequest;
import com.charly.market.notice.model.dto.NoticeResponse;

import java.util.List;

public interface NoticeService {
    void createNotice(CreateNoticeRequest request);
    List<NoticeResponse> findAll();
    NoticeResponse findByNoticeId(Long noticeId);
    void delete(Long noticeId);
}

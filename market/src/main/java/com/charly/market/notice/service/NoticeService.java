package com.charly.market.notice.service;

import com.charly.market.notice.model.dto.CreateNoticeRequest;
import com.charly.market.notice.model.dto.NoticeListResponse;
import com.charly.market.notice.model.entity.Notice;

import java.util.List;

public interface NoticeService {
    void createNotice(CreateNoticeRequest request);
    List<NoticeListResponse> findAll();
}

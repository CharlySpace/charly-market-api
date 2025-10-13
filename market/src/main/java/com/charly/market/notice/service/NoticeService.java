package com.charly.market.notice.service;

import com.charly.market.notice.model.dto.ChangeContentRequest;
import com.charly.market.notice.model.dto.CreateNoticeRequest;
import com.charly.market.notice.model.dto.NoticeResponse;
import com.charly.market.notice.model.dto.NoticeSearchRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NoticeService {
    void createNotice(CreateNoticeRequest request);
    List<NoticeResponse> findAll();
    NoticeResponse findById(Long id);
    void delete(Long id);
    void changeContent(ChangeContentRequest req);
    Page<NoticeResponse> noticeSearch(NoticeSearchRequest request);
}

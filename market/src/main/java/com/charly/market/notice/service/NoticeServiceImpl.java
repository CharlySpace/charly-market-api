package com.charly.market.notice.service;

import com.charly.market.notice.model.dto.ChangeContentRequest;
import com.charly.market.notice.model.dto.CreateNoticeRequest;
import com.charly.market.notice.model.dto.NoticeResponse;
import com.charly.market.notice.model.dto.NoticeSearchRequest;
import com.charly.market.notice.model.entity.Notice;
import com.charly.market.notice.repository.NoticeRepository;
import com.charly.market.user.service.util.UserFinder;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.data.domain.Page;
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
    private final UserFinder userFinder;

    @Override
    public void createNotice(CreateNoticeRequest request) {
        Notice notice = Notice.builder()
                .title(request.title())
                .content(request.content())
                .status(request.status())
                .adminUser(userFinder.getById(request.adminId()))
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
                    notice.getStatus(),
                    notice.getAdminUser().getId()
            );
            noticeResponseList.add(noticeResponse);
        }
        return noticeResponseList;
    }

    @Override
    public NoticeResponse findById(Long id) {
        return noticeRepository.findById(id)
                .map(notice -> new NoticeResponse(
                        notice.getTitle(),
                        notice.getContent(),
                        notice.getStatus(),
                        notice.getAdminUser().getId()))
                .orElseThrow(() -> new EntityNotFoundException("Notice not found with id: " + id));
    }


    @Transactional
    @Override
    public void delete(Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notice not found with id: " + id));
        notice.deactivatedNoticeStatus();
    }

    //업데이트
    @Transactional
    @Override
    public void changeContent(ChangeContentRequest req) {
        Notice notice = noticeRepository.findById(req.contentId()).orElseThrow();

        notice.changeNoticeContent(req.content());
    }

    //페이징
    @Override
    public Page<NoticeResponse> noticeSearch(NoticeSearchRequest request) {
        return noticeRepository.search(request)
                .map(notice -> new NoticeResponse(
                        notice.getTitle(),
                        notice.getContent(),
                        notice.getStatus(),
                        notice.getAdminUser().getId()
                ));
    }

}

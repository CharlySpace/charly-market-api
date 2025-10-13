package com.charly.market.notice.service;

import com.charly.market.notice.model.dto.CreateNoticeRequest;
import com.charly.market.notice.model.dto.NoticeResponse;
import com.charly.market.notice.model.entity.Notice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class NoticeServiceImplTest {

    @Autowired
    private NoticeService noticeService;

    @Test
    public void createNotice() {
        for (long i = 1; i < 6; i++) {
            //given
            CreateNoticeRequest request = new CreateNoticeRequest("제목" + i, "내용" + i, "Y", i);

            noticeService.createNotice(request);

            List<NoticeResponse> noticeResponses = noticeService.findAll();
            System.out.println(noticeResponses);
        }
    }
}
package com.charly.market.notice.controller;

import com.charly.market.notice.model.dto.CreateNoticeRequest;
import com.charly.market.notice.model.dto.NoticeListResponse;
import com.charly.market.notice.model.entity.Notice;
import com.charly.market.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/Notice")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping()
    public ResponseEntity<List<NoticeListResponse>> findNoticeList(){
        List<NoticeListResponse> noticeListResponseList = noticeService.findAll();
        return ResponseEntity.ok(noticeListResponseList);
    }

    @PostMapping()
    public ResponseEntity<String> createNotice(@RequestBody CreateNoticeRequest request){
        System.out.println(request.toString());
        noticeService.createNotice(request);
        return ResponseEntity.ok("공지사항");
    }
}

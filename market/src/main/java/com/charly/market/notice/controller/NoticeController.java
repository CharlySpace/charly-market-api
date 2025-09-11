package com.charly.market.notice.controller;

import com.charly.market.notice.model.dto.CreateNoticeRequest;
import com.charly.market.notice.model.dto.NoticeResponse;
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
    public ResponseEntity<List<NoticeResponse>> findNoticeList(){
        List<NoticeResponse> noticeListResponse = noticeService.findAll();
        return ResponseEntity.ok(noticeListResponse);
    }
    // 검색
    // 파라미터 아이디를 가져옴
    @GetMapping("/{noticeId}")
    public ResponseEntity<NoticeResponse> findNoticeById(@PathVariable Long noticeId){
        NoticeResponse noticeResponse = noticeService.findByNoticeId(noticeId);
        return ResponseEntity.ok(noticeResponse);

    }

    @PostMapping()
    public ResponseEntity<String> createNotice(@RequestBody CreateNoticeRequest request){
        System.out.println(request.toString());
        noticeService.createNotice(request);
        return ResponseEntity.ok("공지사항");
    }

    @DeleteMapping("/{noticeId}")
    public ResponseEntity<String> deleteNotice(@PathVariable Long noticeId){
        noticeService.delete(noticeId);
        return ResponseEntity.ok("삭제 성공");
    }

}

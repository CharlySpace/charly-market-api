package com.charly.market.notice.controller;

import com.charly.market.notice.model.dto.ChangeContentRequest;
import com.charly.market.notice.model.dto.CreateNoticeRequest;
import com.charly.market.notice.model.dto.NoticeResponse;
import com.charly.market.notice.service.NoticeService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/notice")
public class NoticeController {

    private final NoticeService noticeService;

    //조회
    @GetMapping()
    public ResponseEntity<List<NoticeResponse>> findNoticeList(){
        List<NoticeResponse> noticeListResponse = noticeService.findAll();
        return ResponseEntity.ok(noticeListResponse);
    }
    // 검색
    // 파라미터 아이디를 가져옴
    @GetMapping("/{id}")
    public ResponseEntity<NoticeResponse> findNoticeById(@PathVariable @NotNull Long id){
        NoticeResponse noticeResponse = noticeService.findById(id);
        return ResponseEntity.ok(noticeResponse);

    }

    @PostMapping()
    public ResponseEntity<String> createNotice(@RequestBody CreateNoticeRequest request){
        System.out.println(request.toString());
        noticeService.createNotice(request);
        return ResponseEntity.ok("공지사항");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotice(@PathVariable Long id){
        noticeService.delete(id);
        return ResponseEntity.ok("삭제 성공");
    }

    //업데이트
    @PatchMapping("/content")
    public ResponseEntity<String> changeContent(@RequestBody ChangeContentRequest req){
        noticeService.changeContent(req);
        return ResponseEntity.ok("변경 성공");
    }
}

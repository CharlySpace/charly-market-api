package com.charly.market.alarm_template.controller;

import com.charly.market.alarm_template.model.dto.AlarmTemplateChangeContentRequest;
import com.charly.market.alarm_template.model.dto.AlarmTemplateRequest;
import com.charly.market.alarm_template.model.dto.AlarmTemplateResponse;
import com.charly.market.alarm_template.model.dto.CreateAlarmTemplateRequest;
import com.charly.market.alarm_template.model.entity.AlarmTemplate;
import com.charly.market.alarm_template.service.AlarmTemplateService;
import com.charly.market.alarmbox.model.dto.ChangeStatusRequest;
import com.charly.market.alarmbox.model.dto.CreateAlarmBoxRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping( "/api/v1/alarmTemplate")
public class AlarmTemplateController {
    private final AlarmTemplateService alarmTemplateService;

    // 생성
    @PostMapping()
    public ResponseEntity<String> createAlarmTemplate(@RequestBody CreateAlarmTemplateRequest req) {
        System.out.println(req.toString());
        alarmTemplateService.createAlarmTemplate(req);
        return ResponseEntity.ok("알림 템플릿 생성 성공");
    }

    // 조회
    @GetMapping()
    public ResponseEntity<List<AlarmTemplateResponse>> findAlarmTemplateList() {

        List<AlarmTemplateResponse> alarmTemplateResponseList = alarmTemplateService.findAll();
        return ResponseEntity.ok(alarmTemplateResponseList);
    }

    //검색
    @GetMapping("/{AlarmTemplateId}")
    public ResponseEntity<AlarmTemplateResponse> findAlarmTemplateById(@PathVariable Long AlarmTemplateId) {
        AlarmTemplateResponse alarmTemplateResponse = alarmTemplateService.findByAlarmTemplateId(AlarmTemplateId);
        return ResponseEntity.ok(alarmTemplateResponse);
    }

    // 삭제
    @DeleteMapping("/{AlarmTemplateId}")
    public ResponseEntity<String> deleteAlarmTemplate(@PathVariable Long AlarmTemplateId) {
        alarmTemplateService.delete(AlarmTemplateId);
        return ResponseEntity.ok("삭제 성공");
    }

    //수정
    @PatchMapping("/content")
    public ResponseEntity<String> changeAlarmTemplateContent(@RequestBody AlarmTemplateChangeContentRequest req) {
        alarmTemplateService.changeContent(req);
        return ResponseEntity.ok("변경 성공");
    }

}
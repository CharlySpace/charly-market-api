package com.charly.market.alarmbox.controller;


import com.charly.market.alarmbox.model.dto.AlarmBoxResponse;
import com.charly.market.alarmbox.model.dto.AlarmBoxRequest;
import com.charly.market.alarmbox.model.dto.ChangeStatusRequest;
import com.charly.market.alarmbox.service.AlarmBoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/alarmbox")
public class AlarmController {
    private final AlarmBoxService alarmBoxService;

    @GetMapping()
    public ResponseEntity<List<AlarmBoxResponse>> findAlarmBoxList() {

        List<AlarmBoxResponse> alarmBoxListResponseList = alarmBoxService.findAll();
        return ResponseEntity.ok(alarmBoxListResponseList);
    }

    @GetMapping("/{AlarmBoxId}")
    public ResponseEntity<AlarmBoxResponse> findAlarmBoxById(@PathVariable Long AlarmBoxId){
        AlarmBoxResponse alarmBoxResponse = alarmBoxService.findByAlarmBoxId(AlarmBoxId);
        return ResponseEntity.ok(alarmBoxResponse);
    }



    @PostMapping()
    public ResponseEntity<String> createAlarmBox(@RequestBody AlarmBoxRequest req) {
        System.out.println(req.toString());
        alarmBoxService.createAlarmBox(req);

        return ResponseEntity.ok("알람 발송 성공");
    }
    @DeleteMapping("/{AlarmBoxId}")
    public ResponseEntity<String> deleteAlarmBox(@PathVariable Long AlarmBoxId) {
        alarmBoxService.delete(AlarmBoxId);
        return ResponseEntity.ok("삭제 성공");
    }

    @PatchMapping("/status")
    public ResponseEntity<String> changeStatus(@RequestBody ChangeStatusRequest req) {
        alarmBoxService.changeStatus(req);
        return ResponseEntity.ok("변경 성공");

    }
}
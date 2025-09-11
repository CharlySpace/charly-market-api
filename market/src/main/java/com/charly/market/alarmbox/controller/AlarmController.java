package com.charly.market.alarmbox.controller;


import com.charly.market.alarmbox.model.dto.AlarmBoxDtos;
import com.charly.market.alarmbox.service.AlarmBoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/alarmbox")
public class AlarmController {
    private final AlarmBoxService alarmBoxService;

    @PostMapping()
    public ResponseEntity<String> signUp(@RequestBody AlarmBoxDtos req) {
        System.out.println(req.toString());
        alarmBoxService.CreateAlarmBox(req);

        return ResponseEntity.ok("알람 발송 성공");
    }
}

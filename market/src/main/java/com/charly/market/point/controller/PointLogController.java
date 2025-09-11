package com.charly.market.point.controller;

import com.charly.market.point.model.dto.CreatePointLogRequest;
import com.charly.market.point.service.PointLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/point")
public class PointLogController {
    private final PointLogService pointLogService;
    @PostMapping()
    public ResponseEntity<String> Serv(@RequestBody CreatePointLogRequest req){
        System.out.println(req.toString());
        pointLogService.Serv(req);
        return ResponseEntity.ok("PointLog 입력 성공");
    }
}

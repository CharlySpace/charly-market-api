package com.charly.market.point.controller;

import com.charly.market.point.model.dto.CreatePointLogRequest;
import com.charly.market.point.model.dto.PointLogResponse;
import com.charly.market.point.service.PointLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping()
    public ResponseEntity<List<PointLogResponse>> findPointLog(){
        List<PointLogResponse> pointLogResponseList = pointLogService.findAll();
        return ResponseEntity.ok(pointLogResponseList);
    }
}

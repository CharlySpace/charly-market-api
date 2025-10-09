package com.charly.market.report.controller;

import com.charly.market.report.model.dto.AnswerReportRequest;
import com.charly.market.report.model.dto.CreateReportRequest;
import com.charly.market.report.model.dto.ReportResponse;
import com.charly.market.report.repository.ReportRepository;
import com.charly.market.report.service.ReportService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/report")
public class ReportController {
    private final ReportService reportService;
    private final ReportRepository reportRepository;

    //등록
    @PostMapping
    public ResponseEntity<String> createReport(@RequestBody CreateReportRequest request){
        System.out.println(request.toString());
        reportService.createReport(request);
        return ResponseEntity.ok("신고");
    }

    //조회
    @GetMapping
    public ResponseEntity<List<ReportResponse>> findReportList() {
        List<ReportResponse> reportListResponses = reportService.findAll();
        return ResponseEntity.ok(reportListResponses);
    }
    //검색
    @GetMapping("/{id}")
    public ResponseEntity<ReportResponse>  findCategoryById(@PathVariable @NotNull Long id){
        ReportResponse reportResponse = reportService.findById(id);
        return ResponseEntity.ok(reportResponse);
    }
    //신고처리
    @PatchMapping("/{id}/answer")
    public ResponseEntity<String> answerReport(
            @PathVariable Long id,
            @RequestBody AnswerReportRequest request
    ){
        reportService.answerReport(id, request.adminId(), request.action());
        return ResponseEntity.ok("신고 처리 완료 성공");
    }
}

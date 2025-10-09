package com.charly.market.report.service;

import com.charly.market.report.model.dto.CreateReportRequest;
import com.charly.market.report.model.dto.ReportResponse;

import java.util.List;

public interface ReportService {

    //신고 등록
    void createReport(CreateReportRequest request);

    //신고 조회
    List<ReportResponse> findAll();

    //신고 검색
    ReportResponse findById(Long id);

    //신고 처리 완료
    void answerReport (Long reportId, Long adminId, String answer);
}

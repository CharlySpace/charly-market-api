package com.charly.market.Report.service;

import com.charly.market.Report.model.dto.CreateReportRequest;
import com.charly.market.Report.model.dto.ReportResponse;

import java.util.List;

public interface ReportService {

    //신고 등록
    void createReport(CreateReportRequest request);

    //신고 조회
    List<ReportResponse> findAll();

    //신고 검색
    ReportResponse findById(Long id);

    //신고 삭제
    void delete(Long id);
}

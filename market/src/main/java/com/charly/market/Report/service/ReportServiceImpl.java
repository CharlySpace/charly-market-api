package com.charly.market.Report.service;

import com.charly.market.Report.model.dto.CreateReportRequest;
import com.charly.market.Report.model.dto.ReportResponse;
import com.charly.market.Report.model.entity.Report;
import com.charly.market.Report.repository.ReportRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    public void createReport(CreateReportRequest request) {
        Report report = Report.builder()
                .content(request.content())
                .status(request.status())
                .action(request.action())
                .build();

        reportRepository.save(report);

    }

    @Override
    public List<ReportResponse> findAll() {
        List<Report> reports = reportRepository.findAll();
        List<ReportResponse> reportResponseList = new ArrayList<>();
        for (Report report : reports) {
            ReportResponse reportResponse = new ReportResponse(
                    report.getContent(),
                    report.getStatus(),
                    report.getAction()
            );
            reportResponseList.add(reportResponse);
        }
        return reportResponseList;
    }

    @Override
    public ReportResponse findById(Long id) {
        return reportRepository.findById(id)
                .map(report -> new ReportResponse(
                        report.getContent(),
                        report.getStatus(),
                        report.getAction()))
                .orElseThrow(() -> new EntityNotFoundException("Report not found with id: " + id));
    }

    @Override
    public void delete(Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Report not found with id: " + id));
        report.deactivatedCategoryStatus();
    }
}

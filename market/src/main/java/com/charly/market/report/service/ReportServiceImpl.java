package com.charly.market.report.service;

import com.charly.market.report.model.dto.CreateReportRequest;
import com.charly.market.report.model.dto.ReportResponse;
import com.charly.market.report.model.dto.ReportSearchRequest;
import com.charly.market.report.model.entity.Report;
import com.charly.market.report.repository.ReportRepository;
import com.charly.market.auction_item.service.util.AuctionItemFinder;
import com.charly.market.category.service.util.CategoryFinder;
import com.charly.market.user.model.entity.User;
import com.charly.market.user.service.util.UserFinder;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final UserFinder userFinder;
    private final AuctionItemFinder auctionItemFinder;
    private final CategoryFinder categoryFinder;

    @Override
    public void createReport(CreateReportRequest request) {
        Report report = Report.builder()
                .category(categoryFinder.getById(request.categoryId()))
                .auctionItem(auctionItemFinder.getById(request.auctionId()))
                .content(request.content())
                .status(request.status())
                .reportUser(userFinder.getById(request.reporterId()))
                .build();

        reportRepository.save(report);

    }

    @Override
    public List<ReportResponse> findAll() {
        List<Report> reports = reportRepository.findAll();
        List<ReportResponse> reportResponseList = new ArrayList<>();
        for (Report report : reports) {
            ReportResponse reportResponse = new ReportResponse(
                    report.getCategory().getId(),
                    report.getAuctionItem().getId(),
                    report.getContent(),
                    report.getStatus(),
                    report.getReportUser().getId(),
                    report.getAction(),
                    report.getAdminUser() != null ? report.getAdminUser().getId() : null
            );
            reportResponseList.add(reportResponse);
        }
        return reportResponseList;
    }

    @Override
    public ReportResponse findById(Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Report not found with id: " + id));

        return new ReportResponse(
                report.getCategory().getId(),
                report.getAuctionItem().getId(),
                report.getContent(),
                report.getStatus(),
                report.getReportUser().getId(),
                report.getAction(),
                report.getAdminUser() != null ? report.getAdminUser().getId() : null
        );
    }

    // 신고 처리 완료 상태로 만들기 "Y" -> "N"
    @Transactional
    @Override
    public void answerReport(Long reportId, Long adminId, String answer) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new EntityNotFoundException("Report not found with id: " + reportId));

        User admin = userFinder.getById(adminId);
        report.answerReport(admin, answer);
    }


    // 페이징
    @Override
    public Page<ReportResponse> reportSearch(ReportSearchRequest request) {
        return reportRepository.search(request)
                .map(report -> new ReportResponse(
                        report.getCategory().getId(),
                        report.getAuctionItem().getId(),
                        report.getContent(),
                        report.getStatus(),
                        report.getReportUser().getId(),
                        report.getAction(),
                        report.getAdminUser().getId()


                ));
    }


}

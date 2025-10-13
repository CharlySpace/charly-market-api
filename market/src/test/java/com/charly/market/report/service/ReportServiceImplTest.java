package com.charly.market.report.service;

import com.charly.market.report.model.dto.CreateReportRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReportServiceImplTest {

    @Autowired
    private ReportService reportService;

    @Test
    public void createReport() {
        for (long i = 1; i < 6; i++) {
            long j = i + 1;
            CreateReportRequest createReportRequest =
                    new CreateReportRequest(i, i, "내용" + i, "N", i, "처리 완료" + i , j);

            reportService.createReport(createReportRequest);
        }
    }
}

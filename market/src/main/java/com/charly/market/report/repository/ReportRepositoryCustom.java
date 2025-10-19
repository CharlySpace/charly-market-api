package com.charly.market.report.repository;

import com.charly.market.report.model.dto.ReportSearchRequest;
import com.charly.market.report.model.entity.Report;
import org.springframework.data.domain.Page;

public interface ReportRepositoryCustom {
    Page<Report> search (ReportSearchRequest request);
}

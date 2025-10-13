package com.charly.market.point.repository;

import com.charly.market.point.model.PointLog;
import com.charly.market.point.model.dto.PointLogSearchRequest;
import org.springframework.data.domain.Page;

public interface PointLogRepositoryCustom {
    Page<PointLog> search(PointLogSearchRequest request);
}

package com.charly.market.point.repository;

import com.charly.market.point.model.PointLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointLogRepository  extends JpaRepository<PointLog,Long> {

    //PointLog findByPointLogId(Long Id);
}

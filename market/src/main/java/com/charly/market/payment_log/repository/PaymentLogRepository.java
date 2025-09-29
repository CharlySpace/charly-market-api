package com.charly.market.payment_log.repository;

import com.charly.market.payment_log.model.entity.PaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentLogRepository extends JpaRepository<PaymentLog, Long> {
}

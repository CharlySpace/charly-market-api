package com.charly.market.payment_log.repository;

import com.charly.market.payment_log.model.dto.PaymentLogSearchRequest;
import com.charly.market.payment_log.model.entity.PaymentLog;
import org.springframework.data.domain.Page;

public interface PaymentLogRepositoryCustom {
    Page<PaymentLog> search(PaymentLogSearchRequest request);
}

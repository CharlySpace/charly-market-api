package com.charly.market.payment_log.service;

import com.charly.market.payment_log.model.dto.CreatePaymentLogRequest;
import com.charly.market.payment_log.model.dto.PaymentLogResponse;

import java.util.List;

public interface PaymentLogService {
    List<PaymentLogResponse> getPaymentLogs();
     void createPaymentLog(CreatePaymentLogRequest paymentLog);
     //oid deletePaymentLog(Long id);
    //PaymentLog getPaymentLogById(Long id);
}

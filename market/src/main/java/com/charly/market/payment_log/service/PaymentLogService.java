package com.charly.market.payment_log.service;

import com.charly.market.payment_log.model.dto.CreatePaymentLogRequest;
import com.charly.market.payment_log.model.dto.PaymentLogResponse;
import com.charly.market.payment_log.model.dto.PaymentLogSearchRequest;
import java.util.List;
import org.springframework.data.domain.Page;

public interface PaymentLogService {

  List<PaymentLogResponse> getPaymentLogs();

  void createPaymentLog(CreatePaymentLogRequest paymentLog);

  Page<PaymentLogResponse> searchPaymentLogs(PaymentLogSearchRequest paymentLogSearchRequest);
  //oid deletePaymentLog(Long id);
  //PaymentLog getPaymentLogById(Long id);
}

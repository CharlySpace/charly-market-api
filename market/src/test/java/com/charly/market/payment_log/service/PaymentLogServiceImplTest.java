package com.charly.market.payment_log.service;

import com.charly.market.payment_log.model.dto.CreatePaymentLogRequest;
import com.charly.market.payment_log.model.dto.PaymentLogResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PaymentLogServiceImplTest {

  @Autowired
  private PaymentLogService paymentLogService;

  @Test
  public void createPaymentLog() {
    for (long i = 1; i < 6; i++) {
      String type = "충전";
      int paymentAmount = (int) i * 1000;
      int conversionAmount = (int) i * 1000;
      String gradeName = "B";
      CreatePaymentLogRequest request = new CreatePaymentLogRequest(
          type, i, i, paymentAmount, conversionAmount, gradeName
      );
      paymentLogService.createPaymentLog(request);
    }
    List<PaymentLogResponse> response = paymentLogService.getPaymentLogs();
    System.out.println(response);
  }
}

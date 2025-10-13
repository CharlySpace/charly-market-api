package com.charly.market.payment_log.controller;

import com.charly.market.admin.log.model.dto.PageResponse;
import com.charly.market.payment_log.model.dto.CreatePaymentLogRequest;
import com.charly.market.payment_log.model.dto.PaymentLogResponse;
import com.charly.market.payment_log.model.dto.PaymentLogSearchRequest;
import com.charly.market.payment_log.repository.PaymentLogRepository;
import com.charly.market.payment_log.service.PaymentLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping ("api/v1/paymentLog")
public class PaymentLogController {

    private final PaymentLogService paymentLogService;
    private final PaymentLogRepository paymentLogRepository;

    @GetMapping
    public ResponseEntity<List<PaymentLogResponse>> findAllPaymentLogs() {
        List<PaymentLogResponse>paymentLogResponseList=paymentLogService.getPaymentLogs();
    return ResponseEntity.ok(paymentLogResponseList);
    }
    @GetMapping("/search")
    public PageResponse<PaymentLogResponse> search(PaymentLogSearchRequest request){
        return PageResponse.of(paymentLogService.searchPaymentLogs(request));
    }

    @PostMapping
    public ResponseEntity<String> createPaymentLog(@RequestBody CreatePaymentLogRequest paymentLog) {
        paymentLogService.createPaymentLog(paymentLog);
        return ResponseEntity.ok("결제 이력이 저장되었습니다.");
    }
    @DeleteMapping("{paymentLogId}")
    public ResponseEntity<String> deletePaymentLog(@PathVariable Long paymentLogId) {
        paymentLogRepository.deleteById(paymentLogId);
        return ResponseEntity.ok("삭제 완료");
    }
}

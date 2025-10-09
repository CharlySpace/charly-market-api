package com.charly.market.payment_log.service;

import com.charly.market.account.service.utill.AccountFinder;
import com.charly.market.payment_log.model.dto.CreatePaymentLogRequest;
import com.charly.market.payment_log.model.dto.PaymentLogResponse;
import com.charly.market.payment_log.model.entity.PaymentLog;
import com.charly.market.payment_log.repository.PaymentLogRepository;
import com.charly.market.user.service.util.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PaymentLogServiceImpl implements PaymentLogService {
    private final PaymentLogRepository paymentLogRepository;
    private final AccountFinder accountFinder;
    private final UserFinder userFinder;


    @Override
    public List<PaymentLogResponse> getPaymentLogs() {
        List<PaymentLog> paymentLogEntity = paymentLogRepository.findAll();
        List<PaymentLogResponse> paymentLogResponseList = new ArrayList<>();
        for(PaymentLog paymentLog : paymentLogEntity){
            PaymentLogResponse findAll=new PaymentLogResponse(
                    paymentLog.getType(),
                    paymentLog.getAccount().getId(),
                    paymentLog.getUser().getId(),
                    paymentLog.getPaymentAmount(),
                    paymentLog.getConversionAmount(),
                    paymentLog.getGradeName(),
                    paymentLog.getPointAmount()
            );
            paymentLogResponseList.add(findAll);
        }
        return paymentLogResponseList;
    }

    @Override
    public void createPaymentLog(CreatePaymentLogRequest request) {
        PaymentLog paymentLog = PaymentLog.builder()
                .type(request.type())
                .account(accountFinder.getById(request.accountId()))
                .user(userFinder.getById(request.userId()))
                .paymentAmount(request.paymentAmount())
                .conversionAmount(request.conversionAmount())
                .gradeName(request.gradeName())
                .pointAmount(0)
                .build();
        paymentLogRepository.save(paymentLog);

    }
}

package com.charly.market.point.service;

import com.charly.market.point.model.PointLog;
import com.charly.market.point.model.dto.CreatePointLogRequest;
import com.charly.market.point.model.dto.PointLogResponse;
import com.charly.market.point.repository.PointLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PointLogServiceImpl implements PointLogService {

    private final PointLogRepository pointLogRepository;
    @Override
    public void Serv(CreatePointLogRequest request) {
        PointLog pointLog = PointLog.builder()
                .trade_type(request.trade_type())
                .trade_amount(request.trade_amount())
                .trade_explanation(request.trade_explanation())
                .point_amount(request.point_amount())
                .bid_id(0)
                .user_id(0)
                .build();
        pointLogRepository.save(pointLog);
    }
    @Override
    public List<PointLogResponse> findAll(){
        List<PointLog> pointLogs = pointLogRepository.findAll();
        List<PointLogResponse> pointLogResponseList= new ArrayList<>();
        for(PointLog pointLog:pointLogs){
           PointLogResponse pointLogResponse=new PointLogResponse(
                   pointLog.getTrade_type(),
                   pointLog.getTrade_amount(),
                   pointLog.getTrade_explanation(),
                   pointLog.getPoint_amount()
           );
           pointLogResponseList.add(pointLogResponse);
        }
        return pointLogResponseList;
    }
}

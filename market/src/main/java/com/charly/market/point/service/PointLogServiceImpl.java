package com.charly.market.point.service;

import com.charly.market.point.model.PointLog;
import com.charly.market.point.model.dto.ChangeExplanationRequest;
import com.charly.market.point.model.dto.CreatePointLogRequest;
import com.charly.market.point.model.dto.PointLogResponse;
import com.charly.market.point.repository.PointLogRepository;
import jakarta.persistence.EntityNotFoundException;
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
                .type(request.type())
                .tradeAmount(request.tradeAmount())
                .explanation(request.explanation())
                .pointAmount(request.pointAmount())
                .bidId(0)
                .userId(0)
                .build();
        pointLogRepository.save(pointLog);
    }
    @Override
    public List<PointLogResponse> findAll(){
        List<PointLog> pointLogs = pointLogRepository.findAll();
        List<PointLogResponse> pointLogResponseList= new ArrayList<>();
        for(PointLog pointLog:pointLogs){
           PointLogResponse pointLogResponse = new PointLogResponse(
                   pointLog.getType(),
                   pointLog.getTradeAmount(),
                   pointLog.getExplanation(),
                   pointLog.getPointAmount()
           );
           pointLogResponseList.add(pointLogResponse);
        }
        return pointLogResponseList;
    }

    @Override
    public PointLogResponse findById(Long id) {
        return pointLogRepository.findById(id)
                .map(pointLog ->  new PointLogResponse(
            pointLog.getType(),
            pointLog.getTradeAmount(),
            pointLog.getExplanation(),
            pointLog.getPointAmount())).orElseThrow(()-> new EntityNotFoundException("Point_log not found id:"+id));
    }

    @Transactional
    @Override
    public void changExplanation(ChangeExplanationRequest request) {
       // Optional<PointLog> pointLog= pointLogRepository.findById(request.id());
       // pointLog.map(PointLog::getExplanation)
        PointLog pointLog= pointLogRepository.findById(request.id()).orElseThrow();
        pointLog.changeExplanation(request.newExplanation());//newExplanation은 record타입이기 때문에
        //호출을 위해서 newExplanation()사용? getter()과 같은 기능

    }
}


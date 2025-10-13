package com.charly.market.point.service;

import com.charly.market.auction_bid.service.utill.AuctionBidFinder;
import com.charly.market.point.model.PointLog;
import com.charly.market.point.model.dto.ChangeExplanationRequest;
import com.charly.market.point.model.dto.CreatePointLogRequest;
import com.charly.market.point.model.dto.PointLogResponse;
import com.charly.market.point.model.dto.PointLogSearchRequest;
import com.charly.market.point.repository.PointLogRepository;
import com.charly.market.user.service.util.UserFinder;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PointLogServiceImpl implements PointLogService {

  private final PointLogRepository pointLogRepository;
  private final UserFinder userFinder;
  private final AuctionBidFinder auctionBidFinder;

  @Override
  public void create(CreatePointLogRequest request) {
    PointLog pointLog = PointLog.builder()
        .type(request.type())
        .tradeAmount(request.tradeAmount())
        .explanation(request.explanation())
        .auctionBid(auctionBidFinder.getById(request.auctionBidId()))
        .user(userFinder.getById(request.userId()))
        .pointAmount(request.pointAmount())
        .build();
    pointLogRepository.save(pointLog);
  }

  @Override
  public List<PointLogResponse> findAll() {
    List<PointLog> pointLogs = pointLogRepository.findAll();
    List<PointLogResponse> pointLogResponseList = new ArrayList<>();
    for (PointLog pointLog : pointLogs) {
      PointLogResponse pointLogResponse = new PointLogResponse(
          pointLog.getType(),
          pointLog.getTradeAmount(),
          pointLog.getExplanation(),
          pointLog.getAuctionBid().getId(),
          //   pointLog.getUser().getId(),
          pointLog.getPointAmount()
      );
      pointLogResponseList.add(pointLogResponse);
    }
    return pointLogResponseList;
  }


  @Override
  public Page<PointLogResponse> searchPointLog(PointLogSearchRequest request) {

    return pointLogRepository.search(request)
        .map(pointLog -> new PointLogResponse(
            pointLog.getType(),
            pointLog.getTradeAmount(),
            pointLog.getExplanation(),
            pointLog.getAuctionBid().getId(),
            pointLog.getPointAmount()));
  }

  @Transactional
  @Override
  public void changExplanation(ChangeExplanationRequest request) {
    // Optional<PointLog> pointLog= pointLogRepository.findById(request.id());
    // pointLog.map(PointLog::getExplanation)
    PointLog pointLog = pointLogRepository.findById(request.id()).orElseThrow();
    pointLog.changeExplanation(request.newExplanation());//newExplanation은 record타입이기 때문에
    //호출을 위해서 newExplanation()사용? getter()과 같은 기능

  }
}


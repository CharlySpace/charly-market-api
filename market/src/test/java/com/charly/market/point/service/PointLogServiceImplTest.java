package com.charly.market.point.service;

import com.charly.market.point.model.dto.CreatePointLogRequest;
import com.charly.market.point.model.dto.PointLogResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PointLogServiceImplTest {

  @Autowired
  private PointLogService pointLogService;

  @Test
  public void createPointLog() {
    for (long i = 1; i < 6; i++) {
      String type = "환불";
      String explanation = "설명중" + i;
      //pointAmou
      CreatePointLogRequest request = new CreatePointLogRequest(
          type, i * 100, explanation, i, i, i * 1000
      );
      pointLogService.create(request);
    }
    List<PointLogResponse> response = pointLogService.findAll();
    System.out.println(response);
  }
}
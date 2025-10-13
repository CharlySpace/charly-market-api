package com.charly.market.point.service;

import com.charly.market.point.model.dto.ChangeExplanationRequest;
import com.charly.market.point.model.dto.CreatePointLogRequest;
import com.charly.market.point.model.dto.PointLogResponse;
import com.charly.market.point.model.dto.PointLogSearchRequest;
import java.util.List;
import org.springframework.data.domain.Page;

public interface PointLogService {

  void create(CreatePointLogRequest request);

  List<PointLogResponse> findAll();

  // void delete(Long id);
  Page<PointLogResponse> searchPointLog(PointLogSearchRequest request);

  void changExplanation(ChangeExplanationRequest request);
}

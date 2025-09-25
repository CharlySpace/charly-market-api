package com.charly.market.point.service;

import java.util.List;

import com.charly.market.point.model.dto.ChangeExplanationRequest;
import com.charly.market.point.model.dto.CreatePointLogRequest;
import com.charly.market.point.model.dto.PointLogResponse;

public interface PointLogService {
    void Serv(CreatePointLogRequest request);
    List<PointLogResponse> findAll();
    PointLogResponse findById(Long id);
    void delete(Long id);
    void changExplanation(ChangeExplanationRequest request);
}

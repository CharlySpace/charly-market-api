package com.charly.market.point.controller;

import com.charly.market.admin.log.model.dto.PageResponse;
import com.charly.market.point.model.dto.ChangeExplanationRequest;
import com.charly.market.point.model.dto.CreatePointLogRequest;
import com.charly.market.point.model.dto.PointLogResponse;
import com.charly.market.point.model.dto.PointLogSearchRequest;
import com.charly.market.point.repository.PointLogRepository;
import com.charly.market.point.service.PointLogService;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/point")
public class PointLogController {

  private final PointLogService pointLogService;
  private final PointLogRepository pointLogRepository;

  @GetMapping()
  public ResponseEntity<List<PointLogResponse>> findPointLog() {
    List<PointLogResponse> pointLogResponseList = pointLogService.findAll();
    return ResponseEntity.ok(pointLogResponseList);
  }

  @GetMapping("/search")
  public PageResponse<PointLogResponse> search(PointLogSearchRequest request) {
    return PageResponse.of(pointLogService.searchPointLog(request));
  }

  @PostMapping()
  public ResponseEntity<String> serv(@RequestBody CreatePointLogRequest req) {
    System.out.println(req.toString());
    pointLogService.create(req);
    return ResponseEntity.ok("PointLog 입력 성공");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable @NonNull Long id) {
    pointLogRepository.deleteById(id);
    return ResponseEntity.ok("포인트 기록 삭제 성공");
  }

  @PatchMapping("/explanation")
  public ResponseEntity<String> changeExplanation(@RequestBody ChangeExplanationRequest request) {
    pointLogService.changExplanation(request);
    return ResponseEntity.ok("값 변경 성공");
  }//이후에 type을 patchMapping 으로 밖ㅜ기


}

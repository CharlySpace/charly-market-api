package com.charly.market.grade.controller;

import com.charly.market.alarmbox.model.dto.AlarmBoxResponse;
import com.charly.market.grade.model.dto.CreateGradeRequest;
import com.charly.market.grade.model.dto.GradeResponse;
import com.charly.market.grade.model.dto.UpdateGradeRequest;
import com.charly.market.grade.service.GradeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/grade")
public class GradeController {

  private final GradeService gradeService;

  @GetMapping()
  public ResponseEntity<List<GradeResponse>> findAll() {
    return ResponseEntity.ok(gradeService.findAll());
  }

  @PostMapping()
  public ResponseEntity<String> createGrade(@RequestBody CreateGradeRequest request) {
    gradeService.createGrade(request);
    return ResponseEntity.ok("생성 완료");
  }

  @PutMapping()
  public ResponseEntity<String> updateGrade(@RequestBody UpdateGradeRequest request) {
    gradeService.updateGrade(request);
    return ResponseEntity.ok("변경 완료");
  }

  @DeleteMapping("/{gradeId}")
  public ResponseEntity<String> deleteGrade(@PathVariable Long gradeId) {
    gradeService.deleteById(gradeId);
    return ResponseEntity.ok("삭제 완료");
  }
}

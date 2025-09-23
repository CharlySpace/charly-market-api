package com.charly.market.grade.service;

import com.charly.market.grade.model.dto.CreateGradeRequest;
import com.charly.market.grade.model.dto.GradeResponse;
import com.charly.market.grade.model.dto.UpdateGradeRequest;
import java.util.List;

public interface GradeService {
  void createGrade(CreateGradeRequest request);
  List<GradeResponse> findAll();
  void updateGrade(UpdateGradeRequest request);
  void deleteById(Long gradeId);
}

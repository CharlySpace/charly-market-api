package com.charly.market.grade.service;

import com.charly.market.grade.model.dto.CreateGradeRequest;
import com.charly.market.grade.model.dto.GradeResponse;
import com.charly.market.grade.model.dto.UpdateGradeRequest;
import com.charly.market.grade.model.entity.Grade;
import com.charly.market.grade.repository.GradeRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GradeServiceImpl implements GradeService {

  private final GradeRepository gradeRepository;

  @Override
  public void createGrade(CreateGradeRequest request) {
    Grade grade = Grade.builder()
                       .name(request.name())
                       .feeRate(request.feeRate())
                       .standard(request.standard())
                       .build();

    gradeRepository.save(grade);
  }

  @Override
  public List<GradeResponse> findAll() {
    List<Grade> grades = gradeRepository.findAll();
    List<GradeResponse> responses = new ArrayList<>();

    for (Grade grade : grades) {
      responses.add(new GradeResponse(grade.getName(), grade.getFeeRate(), grade.getStandard()));
    }

    return responses;
  }

  @Override
  @Transactional
  public void updateGrade(UpdateGradeRequest request) {
    Grade grade = gradeRepository.findById(request.id()).orElseThrow();
    grade.updateGrade(request);
  }

  @Override
  @Transactional
  public void deleteById(Long gradeId) {
    gradeRepository.deleteById(gradeId);
  }
}

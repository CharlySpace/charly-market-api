package com.charly.market.grade.service;

import com.charly.market.grade.model.entity.Grade;
import com.charly.market.grade.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultGradeProvider {
  private final GradeRepository gradeRepository;

  public Grade getDefaultGrade() {
    return gradeRepository.findByName("B");
  }
}


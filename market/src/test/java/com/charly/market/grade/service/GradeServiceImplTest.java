package com.charly.market.grade.service;

import static org.junit.jupiter.api.Assertions.*;

import com.charly.market.grade.model.dto.CreateGradeRequest;
import com.charly.market.grade.model.dto.GradeResponse;
import com.charly.market.grade.model.entity.Grade;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GradeServiceImplTest {

  @Autowired
  private GradeService gradeService;

  @Test
  public void createGrade() {
    for (int i = 5; i < 10; i++) {
      // given
      CreateGradeRequest request = new CreateGradeRequest("name" + i, i, i);

      // when
      gradeService.createGrade(request);

      // then
      List<GradeResponse> grade = gradeService.findAll();
      System.out.println(grade);
    }
  }
}
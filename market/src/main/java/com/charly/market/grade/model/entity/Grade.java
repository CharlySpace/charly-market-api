package com.charly.market.grade.model.entity;

import com.charly.market.grade.model.dto.UpdateGradeRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grade")

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Grade {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column
  private String name;

  @Column
  private int feeRate;

  @Column
  private int standard;

  public void updateGrade(UpdateGradeRequest request) {
    this.name = request.name();
    this.feeRate = request.feeRate();
    this.standard = request.standard();
  }
}

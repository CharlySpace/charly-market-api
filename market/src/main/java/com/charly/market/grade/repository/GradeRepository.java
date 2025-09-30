package com.charly.market.grade.repository;

import com.charly.market.grade.model.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {

  Grade findByName(String name);
}

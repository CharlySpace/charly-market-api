package com.charly.market.alarm_template.repository;

import com.charly.market.alarm_template.model.entity.AlarmTemplate;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlarmTemplateRepository extends JpaRepository<AlarmTemplate,Long>
{

}

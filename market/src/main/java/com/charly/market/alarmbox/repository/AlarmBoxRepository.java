package com.charly.market.alarmbox.repository;

import com.charly.market.alarmbox.model.entity.AlarmBox;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AlarmBoxRepository extends JpaRepository<AlarmBox, Long> {
}

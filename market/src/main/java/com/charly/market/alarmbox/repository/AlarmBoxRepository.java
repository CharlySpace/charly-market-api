package com.charly.market.alarmbox.repository;

import com.charly.market.alarmbox.model.entity.AlarmBox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmBoxRepository extends JpaRepository<AlarmBox,Long> {
    AlarmBox findByAlarmBoxId(Long alarmBoxId);
}

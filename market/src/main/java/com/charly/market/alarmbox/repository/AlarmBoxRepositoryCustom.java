package com.charly.market.alarmbox.repository;

import com.charly.market.alarmbox.model.dto.AlarmBoxSearchRequest;
import com.charly.market.alarmbox.model.entity.AlarmBox;
import org.springframework.data.domain.Page;

public interface AlarmBoxRepositoryCustom {
    Page<AlarmBox> search(AlarmBoxSearchRequest request);
}

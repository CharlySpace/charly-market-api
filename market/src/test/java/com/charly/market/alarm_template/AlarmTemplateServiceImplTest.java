package com.charly.market.alarm_template;

import com.charly.market.alarm_template.model.dto.CreateAlarmTemplateRequest;
import com.charly.market.alarm_template.service.AlarmTemplateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AlarmTemplateServiceImplTest {

    @Autowired
    private AlarmTemplateService alarmTemplateService;

    @Test
    public void createAlarmTemplate() {
        for(long i = 1; i < 6; i++) {

            // given
            CreateAlarmTemplateRequest alarmTemplateRequest =
                    new CreateAlarmTemplateRequest("Y","알람 템플릿 내용" + i, i);

            // when
            alarmTemplateService.createAlarmTemplate(alarmTemplateRequest);

        }

        // then
        alarmTemplateService.findAll().forEach(System.out::println);
    }
}

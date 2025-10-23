package com.charly.market.alarm_box.service;


import com.charly.market.alarmbox.model.dto.CreateAlarmBoxRequest;
import com.charly.market.alarmbox.service.AlarmBoxService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class AlarmBoxServiceImplTest {

    @Autowired
    private AlarmBoxService alarmBoxService;

    @Test
    public void createAlarmBox() {
        for (long i = 1; i <6; i++) {

            // given
            CreateAlarmBoxRequest alarmBoxRequest =
                    new CreateAlarmBoxRequest("Y", "알람내용" + i, i, i, i);


            // when
            alarmBoxService.createAlarmBox(alarmBoxRequest);

            // then
            alarmBoxService.findAll().forEach(System.out::println);



        }
    }
}

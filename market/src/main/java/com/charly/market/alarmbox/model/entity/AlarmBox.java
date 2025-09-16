package com.charly.market.alarmbox.model.entity;

import com.charly.market.global.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AlarmBox extends BaseTimeEntity {

        @Id
        @Column(name = "alarm_box_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long alarmBoxId;

        @Column(unique = true)
        private String alarmCheck;

        @Column(unique = true)
        private String alarmContent;

        @Column
        private String alarmBoxStatus;
        @Column
        private int alarmBlance;
        @Column
        private int alarmCount;
        @Column
        private int alarmPoint;



        public void deactivatedAlarmStatus() {
            this.alarmBoxStatus = "N";
        }
}

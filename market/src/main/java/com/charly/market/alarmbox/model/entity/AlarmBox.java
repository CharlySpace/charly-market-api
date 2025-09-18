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
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column
        private String status;

        @Column
        private String content;


        public void deactivatedAlarmStatus() {
            this.status = "N";

        }

        public void changeAlarmboxStatus(String status) {
            this.status = status;
        }
}

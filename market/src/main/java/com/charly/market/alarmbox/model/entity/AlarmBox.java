package com.charly.market.alarmbox.model.entity;

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
public class AlarmBox {

        @Id
        @Column(name = "alarm_box_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int alarm_box_id;

        @Column(unique = true)
        private String alarm_check;

        @Column(unique = true)
        private String alarm_content;

        //외래키
        private int userId;
        private int alarmId;
}

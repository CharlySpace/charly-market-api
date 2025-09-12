package com.charly.market.point.model;

import com.charly.market.global.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="point_log")

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointLog extends BaseTimeEntity {
    @Id
    @Column(name="point_log_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long point_log_id;

    @Column(nullable=false)
    private String trade_type;

    @Column(nullable=false)
    private Long trade_amount;

    @Column(nullable=true)
    private String trade_explanation;

    @Column
    private int bid_id;
    private int user_id;

    @Column(nullable=false)
    private Long point_amount;

}

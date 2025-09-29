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
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type;

    @Column
    private Long tradeAmount;

    @Column
    private String explanation;

    @Column
    private int bidId;
    private int userId;

    @Column
    private int pointAmount ;//point_amount


    public void changeExplanation(String newExplanation){
        this.explanation=newExplanation;
    }
}

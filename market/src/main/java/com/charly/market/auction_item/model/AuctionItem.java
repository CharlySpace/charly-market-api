package com.charly.market.auction_item.model;

import com.charly.market.global.model.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Table(name = "auction_item")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuctionItem extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_id")
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private long startingPrice;

    private long currentPrice;

    @NotNull
    private long bidUnit;

    private String bidStatus;

    private LocalDateTime auctionStartTime;
    private LocalDateTime auctionEndTime;

    @NotNull
    private String address; // 판매자 주소

    @NotNull
    private String postingStatus = "Y";

    public void changePostingStatus(){
        this.postingStatus = "N";
    }

    // 외래키
    private int categoryId;
    private int userId;
}

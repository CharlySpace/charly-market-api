package com.charly.market.auction_item.model;

import com.charly.market.category.model.entity.Category;
import com.charly.market.global.model.BaseTimeEntity;
import com.charly.market.user.model.entity.User;
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
public class AuctionItem {

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

    private String address; // 판매자 주소

    private String postingStatus;

    public void changePostingStatus(){
        this.postingStatus = "N";
    }

    public void changeContent(String content){
        this.content = content;
    }

    // 외래키
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category categoryId; // 카테고리id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User userId; // 판매자
}

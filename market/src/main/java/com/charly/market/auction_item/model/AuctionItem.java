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
public class AuctionItem extends BaseTimeEntity{

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


    // 외래키
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category; // 카테고리id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User seller; // 판매자


    @PrePersist
    public void setAuctionTimes() {
        // 경매 시작시간 , 종료시간 생성시 자동 생성
        if (this.auctionStartTime == null && this.getCreatedAt() != null) {
            this.auctionStartTime = this.getCreatedAt().plusMinutes(30);
            this.auctionEndTime = this.auctionStartTime.plusHours(5);
        }
    }

    public void changePostingStatus(){
        this.postingStatus = "N";
    }



    public void changeContent(String content) { this.content = content; }
    public void changeTitle(String title) { this.title = title; }
    public void changeStartingPrice(long price) { this.startingPrice = price; }
    public void changeBidUnit(long unit) { this.bidUnit = unit; }
    public void changeAddress(String address) { this.address = address; }
    public void changeCategory(Category category) { this.category = category; }

    // 수정사항이 있으면 updateAt 기준으로 다시 경매시작이랑 끝난는시간 업데이트
    public void updateAuctionTimes(LocalDateTime updatedAt) {
        this.auctionStartTime = updatedAt.plusMinutes(30);
        this.auctionEndTime = this.auctionStartTime.plusHours(5);
    }

    public void changeBidStatus(String status) {
        this.bidStatus = status;
    }
}

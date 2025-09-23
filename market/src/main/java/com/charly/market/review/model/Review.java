package com.charly.market.review.model;

import com.charly.market.global.model.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Table(name = "review")

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @NotNull
    private long reviewStar;
    @NotNull
    private String content;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    private String reviewStatus = "Y";

    // fk
    @Comment("작성자 id")
    private long reviewerId; // 작성자id
    @Comment("판매자 id")
    private long revieweeId; // 판매자id
    @Comment("물품 id")
    private long auctionId; // 물품id

}

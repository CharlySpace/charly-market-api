package com.charly.market.auction_item.model.dto;

import com.charly.market.auction_item.model.AuctionItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuctionItemFindAll {
    private String auctionTitle;
    private String auctionContent;
    private long startingPrice;
    private long bidUnit;
    private LocalDateTime auctionStartTime;
    private LocalDateTime auctionEndTime;
    private String sellerAddress;
    private int categoryId;
    private int userId;
}




package com.charly.market.auction_item.service;

import com.charly.market.auction_item.model.dto.AuctionItemResponse;
import com.charly.market.auction_item.model.dto.CreateAuctionItemRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AuctionItemServiceImplTest {

    @Autowired
    private AuctionItemService auctionItemService;

    @Test
    public void createAuctionItem(){
        for (long i = 1; i < 6; i++) {
            long startPrice = i * 10000;
            long bidUnit = i * 500;

            CreateAuctionItemRequest request
                    = new CreateAuctionItemRequest(
                            "물품제목" + i , "상품설명" + i, startPrice , bidUnit ,
                    "판매자주소" + i +" 번지",i,i
            );

            auctionItemService.create(request);

            List<AuctionItemResponse> itemList = auctionItemService.AuctionItemList();

            System.out.println(itemList);

        }
    }

}
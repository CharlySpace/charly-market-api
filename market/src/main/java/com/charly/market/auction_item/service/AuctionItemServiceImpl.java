package com.charly.market.auction_item.service;

import com.charly.market.auction_item.model.AuctionItem;
import com.charly.market.auction_item.model.dto.AuctionItemListResponse;
import com.charly.market.auction_item.model.dto.CreateAuctionItemRequest;
import com.charly.market.auction_item.repository.AuctionItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuctionItemServiceImpl implements AuctionItemService {

    private final AuctionItemRepository auctionItemRepository;

    @Override
    public void create(CreateAuctionItemRequest request) {

        AuctionItem auctionItem1 = AuctionItem.builder()
                .auctionTitle(request.auctionTitle())
                .auctionContent(request.auctionContent())
                .startingPrice(request.startingPrice())
                .bidUnit(request.bidUnit())
                .sellerAddress(request.sellerAddress())
                .categoryId(3)
                .userId(1)
                .build();

        auctionItemRepository.save(auctionItem1);
    }

    @Override
    public List<AuctionItemListResponse> AuctionItemList() {
        List<AuctionItem> auctionItemEntity = auctionItemRepository.findAll();
        List<AuctionItemListResponse> findAuctionList = new ArrayList<>();

        for(AuctionItem auction : auctionItemEntity){

            AuctionItemListResponse findAll = new AuctionItemListResponse(
                    auction.getAuctionTitle(),
                    auction.getAuctionContent(),
                    auction.getStartingPrice(),
                    auction.getBidUnit(),
                    auction.getAuctionStartTime(),
                    auction.getAuctionEndTime(),
                    auction.getUserId()
            );

            findAuctionList.add(findAll);

        };
        return findAuctionList;
    }


}

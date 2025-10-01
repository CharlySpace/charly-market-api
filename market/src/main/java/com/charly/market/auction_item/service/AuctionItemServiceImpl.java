package com.charly.market.auction_item.service;

import com.charly.market.auction_item.model.AuctionItem;
import com.charly.market.auction_item.model.dto.AuctionItemResponse;
import com.charly.market.auction_item.model.dto.CreateAuctionItemRequest;
import com.charly.market.auction_item.model.dto.UpdateAuctionItemContentRequest;
import com.charly.market.auction_item.repository.AuctionItemRepository;
import com.charly.market.category.model.entity.Category;
import com.charly.market.category.service.CategoryService;
import com.charly.market.category.service.util.CategoryFinder;
import com.charly.market.user.service.util.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuctionItemServiceImpl implements AuctionItemService {

    private final AuctionItemRepository auctionItemRepository;
    private final UserFinder userFinder;
    private final CategoryFinder categoryFinder;



    @Override
    public void create(CreateAuctionItemRequest request) {

        AuctionItem auctionItem1 = AuctionItem.builder()
                .title(request.title())
                .content(request.content())
                .startingPrice(request.startingPrice())
                .bidUnit(request.bidUnit())
                .address(request.address())
                .userId(userFinder.getById(request.userId()))
                .categoryId(categoryFinder.getById(request.categoryId()))
                .postingStatus("Y")
                .build();

        auctionItemRepository.save(auctionItem1);
    }

    @Override
    public List<AuctionItemResponse> AuctionItemList() {
        List<AuctionItem> auctionItemEntity = auctionItemRepository.findAll();
        List<AuctionItemResponse> findAuctionList = new ArrayList<>();

        for(AuctionItem auction : auctionItemEntity){

            AuctionItemResponse findAll = new AuctionItemResponse(
                    auction.getTitle(),
                    auction.getContent(),
                    auction.getStartingPrice(),
                    auction.getBidUnit(),
                    auction.getAuctionStartTime(),
                    auction.getAuctionEndTime(),
                    auction.getUserId().getId()
            );

            findAuctionList.add(findAll);

        };
        return findAuctionList;
    }

    @Override
    public AuctionItemResponse auctionItemSearch(Long id) {

        Optional<AuctionItem> auctionItem = auctionItemRepository.findById(id);

        return auctionItem.map(item -> new AuctionItemResponse(
                item.getTitle(),
                item.getContent(),
                item.getStartingPrice(),
                item.getBidUnit(),
                item.getAuctionStartTime(),
                item.getAuctionEndTime(),
                item.getUserId().getId()
        )).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Long id) {

        Optional<AuctionItem> auctionItemOptional = auctionItemRepository.findById(id);

        auctionItemOptional.ifPresent(auctionItem -> auctionItem.changePostingStatus());

    }

    @Override
    @Transactional
    public void changeContentRequest(Long auctionId ,UpdateAuctionItemContentRequest upa) {
        Optional<AuctionItem> auctionItem = auctionItemRepository.findById(auctionId);
        auctionItem.ifPresent(auctionItem1 -> auctionItem1.changeContent(upa.newContent()));

        //        AuctionItem auctionItem = auctionItemRepository.findById(auctionId).orElseThrow();
//        auctionItem.changeContent(upa.newContent());

//        auctionItemRepository.updateContent(auctionId, upa.newContent());


    }


}

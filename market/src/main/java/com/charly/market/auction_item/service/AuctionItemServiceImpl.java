package com.charly.market.auction_item.service;

import com.charly.market.auction_item.model.AuctionItem;
import com.charly.market.auction_item.model.dto.AuctionItemResponse;
import com.charly.market.auction_item.model.dto.AuctionItemSearchRequest;
import com.charly.market.auction_item.model.dto.CreateAuctionItemRequest;
import com.charly.market.auction_item.model.dto.UpdateAuctionItemRequest;
import com.charly.market.auction_item.repository.AuctionItemRepository;
import com.charly.market.category.service.util.CategoryFinder;
import com.charly.market.user.service.util.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
                .seller(userFinder.getById(request.seller()))
                .category(categoryFinder.getById(request.category()))
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
                    auction.getId(),
                    auction.getTitle(),
                    auction.getContent(),
                    auction.getCategory().getCategoryName(),
                    auction.getStartingPrice(),
                    auction.getCurrentPrice(),
                    auction.getBidUnit(),
                    auction.getBidStatus(),
                    auction.getAuctionStartTime(),
                    auction.getAuctionEndTime(),
                    auction.getSeller().getId()
            );

            findAuctionList.add(findAll);

        };
        return findAuctionList;
    }

    @Override
    public Page<AuctionItemResponse> auctionItemSearch(AuctionItemSearchRequest request) {
        return auctionItemRepository.search(request).map(auctionItem -> new AuctionItemResponse(
                auctionItem.getId(),
                auctionItem.getTitle(),
                auctionItem.getContent(),
                auctionItem.getCategory().getCategoryName(),
                auctionItem.getStartingPrice(),
                auctionItem.getCurrentPrice(),
                auctionItem.getBidUnit(),
                auctionItem.getBidStatus(),
                auctionItem.getAuctionStartTime(),
                auctionItem.getAuctionEndTime(),
                auctionItem.getSeller().getId()
        ));
    }

    @Override
    public AuctionItemResponse auctionItemSearch(Long id) {

        Optional<AuctionItem> auctionItem = auctionItemRepository.findById(id);

        return auctionItem.map(item -> new AuctionItemResponse(
                item.getId(),
                item.getTitle(),
                item.getContent(),
                item.getCategory().getCategoryName(),
                item.getStartingPrice(),
                item.getCurrentPrice(),
                item.getBidUnit(),
                item.getBidStatus(),
                item.getAuctionStartTime(),
                item.getAuctionEndTime(),
                item.getSeller().getId()
        )).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Long id) {

        Optional<AuctionItem> auctionItemOptional = auctionItemRepository.findById(id);

        auctionItemOptional.ifPresent(auctionItem -> auctionItem.changePostingStatus());

    }


    @Transactional
    @Override
    public void updateAuctionItem(Long auctionId, UpdateAuctionItemRequest request) {
        auctionItemRepository.findById(auctionId)
                .ifPresent(auctionItem -> {

                    // 경매 시작 전에만 변경 가능하게
                    if (auctionItem.getAuctionStartTime() != null &&
                            LocalDateTime.now().isAfter(auctionItem.getAuctionStartTime())) {
                        throw new IllegalStateException("수정사항은 경매 시작 전만 가능합니다");
                    }


                    if (request.title() != null) auctionItem.changeTitle(request.title());
                    if (request.content() != null) auctionItem.changeContent(request.content());
                    if (request.startingPrice() != null) auctionItem.changeStartingPrice(request.startingPrice());
                    if (request.bidUnit() != null) auctionItem.changeBidUnit(request.bidUnit());
                    if (request.address() != null) auctionItem.changeAddress(request.address());
                    if (request.categoryId() != null) auctionItem.changeCategory(categoryFinder.getById(request.categoryId()));



                    auctionItem.updateAuctionTimes(LocalDateTime.now());
                });
    }



    @Override
    @Transactional
    public void updateBidStatus(Long auctionId, String statusCode) {
        Optional<AuctionItem> auctionItemOptional = auctionItemRepository.findById(auctionId);
        auctionItemOptional.ifPresent(auctionItem -> auctionItem.changeBidStatus(statusCode));
    }


}

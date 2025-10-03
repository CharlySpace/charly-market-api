package com.charly.market.auction_bid.service;

import com.charly.market.auction_bid.model.AuctionBid;
import com.charly.market.auction_bid.model.dto.BidResponse;
import com.charly.market.auction_bid.model.dto.BidSearchRequest;
import com.charly.market.auction_bid.model.dto.CreateBidRequest;
import com.charly.market.auction_bid.model.dto.SuccessfulBidRequest;
import com.charly.market.auction_bid.repository.AuctionBidRepository;
import com.charly.market.auction_item.service.util.AuctionItemFinder;
import com.charly.market.user.service.util.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuctionBidServiceImpl implements AuctionBidService{
    private final AuctionBidRepository bidRepository;
    private final UserFinder userFinder;
    private final AuctionItemFinder auctionItemFinder;


    @Override
    public void create(CreateBidRequest request) {
        AuctionBid bid = AuctionBid.builder()
                .bidAmount(request.bidAmount())
                .userId(userFinder.getById(request.userId()))
                .auctionId(auctionItemFinder.getById(request.auctionId()))
                .build();

        bidRepository.save(bid);
    }

    @Override
    public List<BidResponse> bidList() {
        List<AuctionBid> bidEntityList = bidRepository.findAll();
        List<BidResponse> bidResponses = new ArrayList<>();

        for (AuctionBid bid : bidEntityList) {

            BidResponse findAll = new BidResponse(
                    bid.getId(),
                    bid.getBidAmount(),
                    bid.getCreatedAt(),
                    bid.getSuccessStatus(),
                    bid.getAuctionId().getId(),
                    bid.getUserId().getId()
            );

            bidResponses.add(findAll);

        }

        return bidResponses;
    }

    @Override
    public Page<BidResponse> searchBidList(BidSearchRequest request) {
       return bidRepository.search(request).map(auctionBid -> new BidResponse(
                auctionBid.getId(),
                auctionBid.getBidAmount(),
                auctionBid.getCreatedAt(),
                auctionBid.getSuccessStatus(),
                auctionBid.getAuctionId().getId(),
                auctionBid.getUserId().getId()
        ));

    }

    @Override
    public BidResponse bidItemSearch(Long bidId) {
        Optional<AuctionBid> bidItem = bidRepository.findById(bidId);
        return bidItem.map(b -> new BidResponse(
                b.getId(),
                b.getBidAmount(),
                b.getCreatedAt(),
                b.getSuccessStatus(),
                b.getAuctionId().getId(),
                b.getUserId().getId()
        )).orElse(null);
    }

    @Override // delete 없을예정
    public void delete(Long bidId) {}

    @Transactional
    @Override
    public void successfulBid(Long bidId, SuccessfulBidRequest sbr) {
        Optional<AuctionBid> bidItem = bidRepository.findById(bidId);
        bidItem.ifPresent(b -> b.successBid());
    }
}

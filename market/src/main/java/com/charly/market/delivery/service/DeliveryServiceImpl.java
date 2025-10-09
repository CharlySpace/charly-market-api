package com.charly.market.delivery.service;

import com.charly.market.auction_item.service.util.AuctionItemFinder;
import com.charly.market.delivery.model.Delivery;
import com.charly.market.delivery.model.dto.CreateDeliveryRequest;
import com.charly.market.delivery.model.dto.DeliveryResponse;
import com.charly.market.delivery.model.dto.DeliverySearchRequest;
import com.charly.market.delivery.model.dto.UpdateDeliveryNo;
import com.charly.market.delivery.repository.DeliveryRepository;
import com.charly.market.review.model.Review;
import com.charly.market.review.model.dto.ReviewResponse;
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
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final UserFinder userFinder;
    private final AuctionItemFinder auctionItemFinder;


    @Override
    public void create(CreateDeliveryRequest request) {
        Delivery d = Delivery.builder()
                .sender(userFinder.getById(request.sender()))
                .receiver(userFinder.getById(request.receiver()))
                .auction(auctionItemFinder.getById(request.auction()))
                .build();

        deliveryRepository.save(d);
    }

    @Override
    public List<DeliveryResponse> deliveryList() {
        List<Delivery> deliveryList = deliveryRepository.findAll();
        List<DeliveryResponse> deliveryResponses = new ArrayList<>();

        for (Delivery delivery : deliveryList) {

            DeliveryResponse findAll = new DeliveryResponse(
                    delivery.getId(),
                    delivery.getAddress(),
                    delivery.getDeliveryNo(),
                    delivery.getDeliveryStatus(),
                    delivery.getRegisteredAt(),
                    delivery.getFinishedAt(),
                    delivery.getSender().getId(),
                    delivery.getReceiver().getId(),
                    delivery.getAuction().getId()
            );

            deliveryResponses.add(findAll);

        }

        return deliveryResponses;
    }

    @Override
    public Page<DeliveryResponse> deliverySearch(DeliverySearchRequest request) {
        return deliveryRepository.search(request).map(delivery -> new DeliveryResponse(
                delivery.getId(),
                delivery.getAddress(),
                delivery.getDeliveryNo(),
                delivery.getDeliveryStatus(),
                delivery.getRegisteredAt(),
                delivery.getFinishedAt(),
                delivery.getSender().getId(),
                delivery.getReceiver().getId(),
                delivery.getAuction().getId()
        ));
    }


    @Override
    public DeliveryResponse findByDeliveryId(Long deliveryId) {
        Optional<Delivery> deliveryItem = deliveryRepository.findById(deliveryId);

        return deliveryItem.map(item -> new DeliveryResponse(
                item.getId(),
                item.getAddress(),
                item.getDeliveryNo(),
                item.getDeliveryStatus(),
                item.getRegisteredAt(),
                item.getFinishedAt(),
                item.getSender().getId(),
                item.getReceiver().getId(),
                item.getAuction().getId()
        )).orElse(null);
    }

    @Transactional
    @Override
    public void deleteById(Long deliveryId) {
        deliveryRepository.deleteById(deliveryId);
    }

    @Transactional
    @Override
    public void updateDeliveryNo(Long deliveryId, UpdateDeliveryNo udn) {
        Optional<Delivery> deliveryItem = deliveryRepository.findById(deliveryId);
        deliveryItem.ifPresent(r -> r.insertNo(udn.deliveryNo()));
    }
}

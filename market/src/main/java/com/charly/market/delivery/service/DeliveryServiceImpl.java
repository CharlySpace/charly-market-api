package com.charly.market.delivery.service;

import com.charly.market.delivery.model.Delivery;
import com.charly.market.delivery.model.dto.CreateDeliveryRequest;
import com.charly.market.delivery.model.dto.DeliveryResponse;
import com.charly.market.delivery.model.dto.UpdateDeliveryNo;
import com.charly.market.delivery.repository.DeliveryRepository;
import com.charly.market.review.model.Review;
import com.charly.market.review.model.dto.ReviewResponse;
import lombok.RequiredArgsConstructor;
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

    @Override
    public void create(CreateDeliveryRequest request) {
        Delivery d = Delivery.builder()
                .sendId(1)
                .receiverId(2)
                .auctionId(1)
                .build();

        deliveryRepository.save(d);
    }

    @Override
    public List<DeliveryResponse> deliveryList() {
        List<Delivery> deliveryList = deliveryRepository.findAll();
        List<DeliveryResponse> deliveryResponses = new ArrayList<>();

        for (Delivery delivery : deliveryList) {

            DeliveryResponse findAll = new DeliveryResponse(
                    delivery.getAddress(),
                    delivery.getDeliveryNo(),
                    delivery.getDeliveryStatus(),
                    delivery.getRegisteredAt(),
                    delivery.getFinishedAt(),
                    delivery.getSendId(),
                    delivery.getReceiverId(),
                    delivery.getAuctionId()
            );

            deliveryResponses.add(findAll);

        }

        return deliveryResponses;
    }

    @Override
    public DeliveryResponse findByDeliveryId(Long deliveryId) {
        Optional<Delivery> deliveryItem = deliveryRepository.findById(deliveryId);

        return deliveryItem.map(item -> new DeliveryResponse(
                item.getAddress(),
                item.getDeliveryNo(),
                item.getDeliveryStatus(),
                item.getRegisteredAt(),
                item.getFinishedAt(),
                item.getSendId(),
                item.getReceiverId(),
                item.getAuctionId()
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

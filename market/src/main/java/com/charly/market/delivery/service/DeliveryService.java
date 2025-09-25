package com.charly.market.delivery.service;

import com.charly.market.delivery.model.dto.CreateDeliveryRequest;
import com.charly.market.delivery.model.dto.DeliveryResponse;
import com.charly.market.delivery.model.dto.UpdateDeliveryNo;

import java.util.List;

public interface DeliveryService {
    void create(CreateDeliveryRequest delivery);
    List<DeliveryResponse> deliveryList();
    DeliveryResponse findByDeliveryId(Long deliveryId);
    void deleteById(Long deliveryId);
    void updateDeliveryNo(Long deliveryId , UpdateDeliveryNo udn);
}

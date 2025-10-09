package com.charly.market.delivery.service;

import com.charly.market.delivery.model.dto.CreateDeliveryRequest;
import com.charly.market.delivery.model.dto.DeliveryResponse;
import com.charly.market.delivery.model.dto.DeliverySearchRequest;
import com.charly.market.delivery.model.dto.UpdateDeliveryStatus;
import com.charly.market.delivery.model.dto.UpdateDeliveryStatus;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DeliveryService {
    void create(CreateDeliveryRequest delivery);
    List<DeliveryResponse> deliveryList();
    Page<DeliveryResponse> deliverySearch(DeliverySearchRequest request);
    DeliveryResponse findByDeliveryId(Long deliveryId);
    void deleteById(Long deliveryId);
    void updateDeliveryStatus(Long deliveryId , UpdateDeliveryStatus udn);
}

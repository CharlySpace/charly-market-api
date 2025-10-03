package com.charly.market.delivery.repository;

import com.charly.market.delivery.model.Delivery;
import com.charly.market.delivery.model.dto.DeliveryResponse;
import com.charly.market.delivery.model.dto.DeliverySearchRequest;
import org.springframework.data.domain.Page;


public interface DeliveryRepositoryCustom {
    Page<Delivery> search(DeliverySearchRequest request);
}

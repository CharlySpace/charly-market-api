package com.charly.market.delivery.model.dto;

public record UpdateDeliveryStatus (
        Long deliveryNo,
        String address,
        String receiveCheck,
        String returnDelivery
){
}

package com.charly.market.delivery.service;

import com.charly.market.delivery.model.dto.CreateDeliveryRequest;
import com.charly.market.delivery.model.dto.DeliveryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DeliveryServiceImplTest {

    @Autowired
    private DeliveryService deliveryService;


    @Test
    public void creatDelivery(){
        for (long i = 1; i < 6; i++) {
            long j = i+1;

            CreateDeliveryRequest createDeliveryRequest = new CreateDeliveryRequest(i,j,i);

            deliveryService.create(createDeliveryRequest);


        }
    }



}
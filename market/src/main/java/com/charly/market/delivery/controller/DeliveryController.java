package com.charly.market.delivery.controller;

import com.charly.market.admin.log.model.dto.PageResponse;
import com.charly.market.delivery.model.dto.CreateDeliveryRequest;
import com.charly.market.delivery.model.dto.DeliveryResponse;
import com.charly.market.delivery.model.dto.DeliverySearchRequest;
import com.charly.market.delivery.model.dto.UpdateDeliveryStatus;
import com.charly.market.delivery.model.dto.UpdateDeliveryStatus;
import com.charly.market.delivery.service.DeliveryService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<String> createReview(@RequestBody CreateDeliveryRequest deliveryRequest){
        String result = deliveryRequest.toString();

        deliveryService.create(deliveryRequest);

        return ResponseEntity.ok("배송 등록" + result);
    }


    @GetMapping
    public ResponseEntity<List<DeliveryResponse>> findDeliveryList(){
        List<DeliveryResponse> deliveryResponses = deliveryService.deliveryList();

        return ResponseEntity.ok(deliveryResponses);
    }

    @GetMapping("/search")
    public PageResponse<DeliveryResponse> deliverySearchList(DeliverySearchRequest request){
        return PageResponse.of(deliveryService.deliverySearch(request));
    }


    @GetMapping("/{deliveryId}")
    public ResponseEntity<DeliveryResponse> findByDeliveryId(@PathVariable Long deliveryId){
        DeliveryResponse deliveryResponse = deliveryService.findByDeliveryId(deliveryId);
        return ResponseEntity.ok(deliveryResponse);
    }


    @DeleteMapping("/{deliveryId}")
    public ResponseEntity<String> delete(@PathVariable Long deliveryId){
        deliveryService.deleteById(deliveryId);
        return ResponseEntity.ok("삭제 완료");
    }


    @PatchMapping("/{deliveryId}")
    public ResponseEntity<String> updateDelivery(
            @PathVariable Long deliveryId,
            @RequestBody UpdateDeliveryStatus request) {

        deliveryService.updateDeliveryStatus(deliveryId, request);

        return ResponseEntity.ok("배송 정보가 수정되었습니다.");
    }

}

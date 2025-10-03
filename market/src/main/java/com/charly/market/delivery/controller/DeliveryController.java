package com.charly.market.delivery.controller;

import com.charly.market.admin.log.model.dto.PageResponse;
import com.charly.market.delivery.model.dto.CreateDeliveryRequest;
import com.charly.market.delivery.model.dto.DeliveryResponse;
import com.charly.market.delivery.model.dto.DeliverySearchRequest;
import com.charly.market.delivery.model.dto.UpdateDeliveryNo;
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


    @PatchMapping("/{deliveryId}/deliveryNo")
    public ResponseEntity<String> addDeliveryNo (@PathVariable Long deliveryId ,@RequestBody UpdateDeliveryNo udn){
        deliveryService.updateDeliveryNo(deliveryId,udn);

        return ResponseEntity.ok("운송장 번호 입력 성공");
    }

}

package com.charly.market.delivery.repository;

import com.charly.market.delivery.model.Delivery;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery,Long> {
}

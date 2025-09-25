package com.charly.market.point.model.dto;

public record PointLogResponse(
            String type,
            Long tradeAmount,
            String explanation,
            int pointAmount

){ }

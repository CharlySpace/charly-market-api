package com.charly.market.point.model.dto;

public record PointLogResponse(
            String trade_type,
            Long trade_amount,
            String trade_explanation,
            Long point_amount

){ }

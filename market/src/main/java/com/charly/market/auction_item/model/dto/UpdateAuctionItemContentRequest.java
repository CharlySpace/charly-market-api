package com.charly.market.auction_item.model.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateAuctionItemContentRequest (
        @NotBlank String newContent ){

}

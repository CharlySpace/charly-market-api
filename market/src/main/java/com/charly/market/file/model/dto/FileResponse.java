package com.charly.market.file.model.dto;

public record FileResponse(
        String path,
        String fileName,
        String type
) {
}

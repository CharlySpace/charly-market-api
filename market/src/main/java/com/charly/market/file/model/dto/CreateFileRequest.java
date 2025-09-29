package com.charly.market.file.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateFileRequest(
        @NotBlank String path,
        @NotBlank String fileName,
        @NotBlank String type
) {
}

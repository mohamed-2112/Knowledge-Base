package com.mrm.knowledge_base.uiCustomization.dto;

import jakarta.validation.constraints.NotBlank;

public record UiCustomizationDraftRequest(
        @NotBlank
        String message,
        @NotBlank
        String page
) {
}

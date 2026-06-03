package com.mrm.knowledge_base.uiCustomization.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ThemeConfigDto(
        String mode,
        @JsonProperty("primary_color")
        String primaryColor,
        String density,
        String radius
) {
}

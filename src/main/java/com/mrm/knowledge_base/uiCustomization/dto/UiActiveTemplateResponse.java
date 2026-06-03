package com.mrm.knowledge_base.uiCustomization.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UiActiveTemplateResponse(
        @JsonProperty("active_template_id")
        String activeTemplateId,
        @JsonProperty("user_id")
        String userId,
        String page,
        String status,
        int version,
        UiTemplateDto template
) {
}

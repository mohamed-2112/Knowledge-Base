package com.mrm.knowledge_base.uiCustomization.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UiApplyDraftResponse(
        String status,
        String message,
        @JsonProperty("draft_id")
        String draftId,
        @JsonProperty("applied_template_id")
        String appliedTemplateId,
        String page,
        int version,
        UiTemplateDto template
) {
}

package com.mrm.knowledge_base.uiCustomization.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UiDraftDetailsResponse(
        @JsonProperty("draft_id")
        String draftId,
        @JsonProperty("agent_run_id")
        String agentRunId,
        @JsonProperty("user_id")
        String userId,
        String page,
        String status,
        UiTemplateDto template
) {
}

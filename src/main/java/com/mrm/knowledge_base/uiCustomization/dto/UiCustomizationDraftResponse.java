package com.mrm.knowledge_base.uiCustomization.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record UiCustomizationDraftResponse(
        String status,
        String explanation,
        @JsonProperty("draft_template")
        UiTemplateDto draftTemplate,
        @JsonProperty("validation_errors")
        List<String> validationErrors,
        @JsonProperty("agent_run_id")
        String agentRunId,
        @JsonProperty("draft_id")
        String draftId,
        @JsonProperty("model_provider")
        String modelProvider,
        @JsonProperty("model_name")
        String modelName,
        int attempts
) {
}

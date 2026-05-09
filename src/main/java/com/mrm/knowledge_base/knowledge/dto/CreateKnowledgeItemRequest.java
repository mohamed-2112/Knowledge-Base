package com.mrm.knowledge_base.knowledge.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record CreateKnowledgeItemRequest(
        @NotBlank(message = "title is required")
        String title,
        String content,
        @NotBlank(message = "Type is required")
        String type,
        @NotNull(message = "Attributes are required")
        Map<String,Object> attributes
) {
}

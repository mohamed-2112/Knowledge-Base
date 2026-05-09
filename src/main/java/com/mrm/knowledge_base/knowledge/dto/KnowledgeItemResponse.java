package com.mrm.knowledge_base.knowledge.dto;

import com.mrm.knowledge_base.knowledge.KnowledgeItem;

import java.time.LocalDateTime;
import java.util.Map;

public record KnowledgeItemResponse(
        Long id,
        String title,
        String content,
        String type,
        Map<String, Object> attributes,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static KnowledgeItemResponse from(KnowledgeItem item){
        return new KnowledgeItemResponse(
                item.getId(),
                item.getTitle(),
                item.getContent(),
                item.getType(),
                item.getAttributes(),
                item.getCreatedAt(),
                item.getUpdatedAt()
        );
    }
}

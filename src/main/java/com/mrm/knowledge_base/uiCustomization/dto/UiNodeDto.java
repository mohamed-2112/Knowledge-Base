package com.mrm.knowledge_base.uiCustomization.dto;

import java.util.List;
import java.util.Map;

public record UiNodeDto(
        String type,
        Map<String, Object> props,
        List<UiNodeDto> children
) {
}

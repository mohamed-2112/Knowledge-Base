package com.mrm.knowledge_base.uiCustomization.dto;

public record UiTemplateDto(
        String page,
        ThemeConfigDto theme,
        UiNodeDto tree
) {
}

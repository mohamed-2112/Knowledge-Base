package com.mrm.knowledge_base.uiCustomization;

import com.mrm.knowledge_base.uiCustomization.dto.*;
import com.mrm.knowledge_base.user.User;
import org.springframework.stereotype.Service;

@Service
public class UiCustomizationService {
    private final AiAgentClient aiAgentClient;

    public UiCustomizationService(AiAgentClient aiAgentClient) {
        this.aiAgentClient = aiAgentClient;
    }

    public UiCustomizationDraftResponse createDraft(
            UiCustomizationDraftRequest request,
            User currentUser
    ) {
        return aiAgentClient.createDraft(
                getAgentUserId(currentUser),
                request
        );
    }

    public UiDraftDetailsResponse getDraft(
            String draftId,
            User currentUser
    ) {
        return aiAgentClient.getDraft(
                getAgentUserId(currentUser),
                draftId
        );
    }

    public UiApplyDraftResponse applyDraft(
            String draftId,
            User currentUser
    ) {
        return aiAgentClient.applyDraft(
                getAgentUserId(currentUser),
                draftId
        );
    }

    public UiActiveTemplateResponse getActiveTemplate(
            String page,
            User currentUser
    ) {
        return aiAgentClient.getActiveTemplate(
                getAgentUserId(currentUser),
                page
        );
    }

    private String getAgentUserId(User currentUser) {
        return String.valueOf(currentUser.getId());
    }
}

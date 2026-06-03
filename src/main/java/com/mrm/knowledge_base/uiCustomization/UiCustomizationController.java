package com.mrm.knowledge_base.uiCustomization;

import com.mrm.knowledge_base.auth.CurrentUserService;
import com.mrm.knowledge_base.uiCustomization.dto.*;
import com.mrm.knowledge_base.user.User;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ui-customization")
public class UiCustomizationController {

    private final UiCustomizationService uiCustomizationService;
    private final CurrentUserService currentUserService;

    public UiCustomizationController(UiCustomizationService uiCustomizationService, CurrentUserService currentUserService) {
        this.uiCustomizationService = uiCustomizationService;
        this.currentUserService = currentUserService;
    }

    @PostMapping("/draft")
    public UiCustomizationDraftResponse createDraft(
            @Valid @RequestBody UiCustomizationDraftRequest request,
            Authentication authentication
            ) {
        User currentUser = currentUserService.getCurrentUser(authentication);
        return uiCustomizationService.createDraft(request,currentUser);
    }

    @GetMapping("/drafts/{draftId}")
    public UiDraftDetailsResponse getDraft(
            @PathVariable String draftId,
            Authentication authentication
    ) {
        User currentUser = currentUserService.getCurrentUser(authentication);
        return uiCustomizationService.getDraft(draftId,currentUser);
    }

    @PostMapping("/drafts/{draftId}/apply")
    public UiApplyDraftResponse  applyDraft(
            @PathVariable String draftId,
            Authentication authentication
    ) {
        User currentUser = currentUserService.getCurrentUser(authentication);
        return uiCustomizationService.applyDraft(draftId,currentUser);
    }

    @GetMapping("/pages/{page}/active")
    public UiActiveTemplateResponse getActiveTemplate(
            @PathVariable String page,
            Authentication authentication
    ) {
        User currentUser = currentUserService.getCurrentUser(authentication);
        return uiCustomizationService.getActiveTemplate(page,currentUser);
    }


}

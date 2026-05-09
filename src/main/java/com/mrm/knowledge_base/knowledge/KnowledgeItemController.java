package com.mrm.knowledge_base.knowledge;

import com.mrm.knowledge_base.auth.CurrentUserService;
import com.mrm.knowledge_base.knowledge.dto.CreateKnowledgeItemRequest;
import com.mrm.knowledge_base.knowledge.dto.KnowledgeItemResponse;
import com.mrm.knowledge_base.knowledge.dto.UpdateKnowledgeItemRequest;
import com.mrm.knowledge_base.user.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/knowledge-items")
public class KnowledgeItemController {

    private final KnowledgeItemService knowledgeItemService;
    private final CurrentUserService currentUserService;

    public KnowledgeItemController(KnowledgeItemService knowledgeItemService, CurrentUserService currentUserService) {
        this.knowledgeItemService = knowledgeItemService;
        this.currentUserService = currentUserService;
    }

    @GetMapping
    public List<KnowledgeItemResponse> getMyKnowledgeItems(Authentication authentication) {
        User currentUser = currentUserService.getCurrentUser(authentication);
        return knowledgeItemService.getMyKnowledgeItems(currentUser);
    }

    @GetMapping("/{id}")
    public KnowledgeItemResponse getMyKnowledgeItem(@PathVariable Long id, Authentication authentication) {
        User currentUser = currentUserService.getCurrentUser(authentication);
        return knowledgeItemService.getMyKnowledgeItem(id, currentUser);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  KnowledgeItemResponse createKnowledgeItem(
            @Valid @RequestBody CreateKnowledgeItemRequest request,
            Authentication authentication) {
        User currentUser = currentUserService.getCurrentUser(authentication);
        return knowledgeItemService.creatKnowledgeItem(request,currentUser);
    }

    @PutMapping("/{id}")
    public KnowledgeItemResponse updateKnowledgeItem(
            @PathVariable Long id,
            @Valid @RequestBody UpdateKnowledgeItemRequest request,
            Authentication authentication) {
        User currentUser = currentUserService.getCurrentUser(authentication);
        return knowledgeItemService.updateKnowledgeItem(id,request,currentUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteKnowledgeItem(@PathVariable Long id, Authentication authentication) {
        User currentUser = currentUserService.getCurrentUser(authentication);
        knowledgeItemService.deleteKnowledgeItem(id, currentUser);

    }

}

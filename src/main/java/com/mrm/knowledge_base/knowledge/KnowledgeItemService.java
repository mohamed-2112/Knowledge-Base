package com.mrm.knowledge_base.knowledge;

import com.mrm.knowledge_base.knowledge.dto.CreateKnowledgeItemRequest;
import com.mrm.knowledge_base.knowledge.dto.KnowledgeItemResponse;
import com.mrm.knowledge_base.knowledge.dto.UpdateKnowledgeItemRequest;
import com.mrm.knowledge_base.user.User;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class KnowledgeItemService {
    private KnowledgeItemRepository knowledgeItemRepository;

    public KnowledgeItemService(KnowledgeItemRepository knowledgeItemRepository) {
        this.knowledgeItemRepository = knowledgeItemRepository;
    }

    /**
     * This method will be called in the get request for all the knowledge items of the current user
     * @param currentUser
     * @return knowledgeItems
     */
    public List<KnowledgeItemResponse> getMyKnowledgeItems(User currentUser) {
        return knowledgeItemRepository.findByOwnerOrderByCreatedAtDesc(currentUser)
                .stream()
                .map(KnowledgeItemResponse::from)
                .toList();
    }

    /**
     * This method will be called in the get request for one knowledge item of the current user
     * @param id
     * @param currentUser
     * @return knowledgeItem
     */
    public KnowledgeItemResponse getMyKnowledgeItem(Long id, User currentUser) {
        KnowledgeItem item = findOwnedItemOrThrow(id, currentUser);
        return KnowledgeItemResponse.from(item);
    }

    /**
     * A Helper function to get the user item or throw and exception
     * @param id
     * @param currentUser
     * @return KnowledgeItem
     */
    private KnowledgeItem findOwnedItemOrThrow(Long id, User currentUser) {
        return knowledgeItemRepository.findByIdAndOwner(id, currentUser)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "Knowledge item not found"
                ));
    }

    /**
     * This method save the new Item in the database
     * @param request
     * @param currentUser
     * @return the new saved item
     */
    public KnowledgeItemResponse creatKnowledgeItem(
            CreateKnowledgeItemRequest request,
            User currentUser
    ) {
        KnowledgeItem item = new KnowledgeItem();
        item.setTitle(request.title());
        item.setContent(request.content());
        item.setType(request.type());
        item.setAttributes(safeAttributes(request.attributes()));
        item.setOwner(currentUser);
        KnowledgeItem savedItem = knowledgeItemRepository.save(item);
        return KnowledgeItemResponse.from(savedItem);

    }

    /**
     * A Helper method that safely return the provided attributes
     * @param attributes
     * @return an empty map if no attributes or the provided attributes if not empty
     */
    private Map<String, Object> safeAttributes(Map<String, Object> attributes) {
        if (attributes == null) {
            return new HashMap<>();
        }
        return attributes;
    }

    /**
     * This method is for updating an item
     * @param id
     * @param request
     * @param currentUser
     * @return the updated item
     */
    public KnowledgeItemResponse updateKnowledgeItem(
            Long id,
            UpdateKnowledgeItemRequest request,
            User currentUser
    ) {
        KnowledgeItem item = findOwnedItemOrThrow(id, currentUser);
        item.setTitle(request.title());
        item.setContent(request.content());
        item.setType(request.type());
        item.setAttributes(safeAttributes(request.attributes()));
        KnowledgeItem updatedItem = knowledgeItemRepository.save(item);
        return KnowledgeItemResponse.from(updatedItem);
    }

    /**
     *  This method is for deleting an item
     * @param id
     * @param currentUser
     */
    public void deleteKnowledgeItem(Long id, User currentUser) {
        KnowledgeItem item = findOwnedItemOrThrow(id, currentUser);
        knowledgeItemRepository.delete(item);
    }




}

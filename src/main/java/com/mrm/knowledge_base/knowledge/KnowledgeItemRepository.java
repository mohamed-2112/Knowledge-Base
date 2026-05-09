package com.mrm.knowledge_base.knowledge;

import com.mrm.knowledge_base.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KnowledgeItemRepository extends JpaRepository<KnowledgeItem, Long> {
    List<KnowledgeItem> findByOwnerOrderByCreatedAtDesc(User owner);
    Optional<KnowledgeItem> findByIdAndOwner(Long id, User owner);

}

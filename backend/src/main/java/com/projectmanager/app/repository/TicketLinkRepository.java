package com.projectmanager.app.repository;

import com.projectmanager.app.model.TicketLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketLinkRepository extends JpaRepository<TicketLink, Long> {
    List<TicketLink> findBySourceTicketId(Long sourceTicketId);
    List<TicketLink> findByTargetTicketId(Long targetTicketId);
}

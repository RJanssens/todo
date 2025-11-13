package com.projectmanager.app.repository;

import com.projectmanager.app.model.Ticket;
import com.projectmanager.app.model.Ticket.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByTicketNumber(String ticketNumber);
    List<Ticket> findByProjectId(Long projectId);
    List<Ticket> findByProjectIdAndStatus(Long projectId, TicketStatus status);
    List<Ticket> findByAssigneeId(Long assigneeId);
    List<Ticket> findByReporterId(Long reporterId);
    boolean existsByTicketNumber(String ticketNumber);
}

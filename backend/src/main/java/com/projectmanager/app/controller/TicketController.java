package com.projectmanager.app.controller;

import com.projectmanager.app.dto.CreateTicketRequest;
import com.projectmanager.app.model.Ticket;
import com.projectmanager.app.model.Ticket.TicketStatus;
import com.projectmanager.app.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @GetMapping("/number/{ticketNumber}")
    public ResponseEntity<Ticket> getTicketByNumber(@PathVariable String ticketNumber) {
        return ResponseEntity.ok(ticketService.getTicketByNumber(ticketNumber));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Ticket>> getTicketsByProjectId(@PathVariable Long projectId) {
        return ResponseEntity.ok(ticketService.getTicketsByProjectId(projectId));
    }

    @GetMapping("/project/{projectId}/status/{status}")
    public ResponseEntity<List<Ticket>> getTicketsByProjectIdAndStatus(
            @PathVariable Long projectId,
            @PathVariable TicketStatus status) {
        return ResponseEntity.ok(ticketService.getTicketsByProjectIdAndStatus(projectId, status));
    }

    @GetMapping("/assignee/{assigneeId}")
    public ResponseEntity<List<Ticket>> getTicketsByAssigneeId(@PathVariable Long assigneeId) {
        return ResponseEntity.ok(ticketService.getTicketsByAssigneeId(assigneeId));
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@Valid @RequestBody CreateTicketRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ticketService.createTicket(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(
            @PathVariable Long id,
            @Valid @RequestBody CreateTicketRequest request) {
        return ResponseEntity.ok(ticketService.updateTicket(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}

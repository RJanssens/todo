package com.projectmanager.app.controller;

import com.projectmanager.app.model.TicketLink;
import com.projectmanager.app.model.TicketLink.LinkType;
import com.projectmanager.app.service.TicketLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ticket-links")
@CrossOrigin(origins = "*")
public class TicketLinkController {

    @Autowired
    private TicketLinkService ticketLinkService;

    @GetMapping("/source/{sourceTicketId}")
    public ResponseEntity<List<TicketLink>> getLinksBySourceTicketId(@PathVariable Long sourceTicketId) {
        return ResponseEntity.ok(ticketLinkService.getLinksBySourceTicketId(sourceTicketId));
    }

    @GetMapping("/target/{targetTicketId}")
    public ResponseEntity<List<TicketLink>> getLinksByTargetTicketId(@PathVariable Long targetTicketId) {
        return ResponseEntity.ok(ticketLinkService.getLinksByTargetTicketId(targetTicketId));
    }

    @PostMapping
    public ResponseEntity<TicketLink> createLink(@RequestBody Map<String, Object> request) {
        Long sourceTicketId = Long.valueOf(request.get("sourceTicketId").toString());
        Long targetTicketId = Long.valueOf(request.get("targetTicketId").toString());
        LinkType linkType = LinkType.valueOf(request.get("linkType").toString());

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ticketLinkService.createLink(sourceTicketId, targetTicketId, linkType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLink(@PathVariable Long id) {
        ticketLinkService.deleteLink(id);
        return ResponseEntity.noContent().build();
    }
}

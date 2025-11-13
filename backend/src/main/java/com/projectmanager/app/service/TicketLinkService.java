package com.projectmanager.app.service;

import com.projectmanager.app.model.Ticket;
import com.projectmanager.app.model.TicketLink;
import com.projectmanager.app.model.TicketLink.LinkType;
import com.projectmanager.app.repository.TicketLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TicketLinkService {

    @Autowired
    private TicketLinkRepository ticketLinkRepository;

    @Autowired
    private TicketService ticketService;

    public List<TicketLink> getLinksBySourceTicketId(Long sourceTicketId) {
        return ticketLinkRepository.findBySourceTicketId(sourceTicketId);
    }

    public List<TicketLink> getLinksByTargetTicketId(Long targetTicketId) {
        return ticketLinkRepository.findByTargetTicketId(targetTicketId);
    }

    public TicketLink createLink(Long sourceTicketId, Long targetTicketId, LinkType linkType) {
        Ticket sourceTicket = ticketService.getTicketById(sourceTicketId);
        Ticket targetTicket = ticketService.getTicketById(targetTicketId);

        if (sourceTicketId.equals(targetTicketId)) {
            throw new RuntimeException("Cannot link a ticket to itself");
        }

        TicketLink link = new TicketLink();
        link.setSourceTicket(sourceTicket);
        link.setTargetTicket(targetTicket);
        link.setLinkType(linkType);

        return ticketLinkRepository.save(link);
    }

    public void deleteLink(Long id) {
        TicketLink link = ticketLinkRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ticket link not found with id: " + id));
        ticketLinkRepository.delete(link);
    }
}

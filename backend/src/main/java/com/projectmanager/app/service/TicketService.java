package com.projectmanager.app.service;

import com.projectmanager.app.dto.CreateTicketRequest;
import com.projectmanager.app.model.*;
import com.projectmanager.app.model.Ticket.TicketStatus;
import com.projectmanager.app.repository.TicketRepository;
import com.projectmanager.app.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagRepository tagRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + id));
    }

    public Ticket getTicketByNumber(String ticketNumber) {
        return ticketRepository.findByTicketNumber(ticketNumber)
            .orElseThrow(() -> new RuntimeException("Ticket not found with number: " + ticketNumber));
    }

    public List<Ticket> getTicketsByProjectId(Long projectId) {
        return ticketRepository.findByProjectId(projectId);
    }

    public List<Ticket> getTicketsByProjectIdAndStatus(Long projectId, TicketStatus status) {
        return ticketRepository.findByProjectIdAndStatus(projectId, status);
    }

    public List<Ticket> getTicketsByAssigneeId(Long assigneeId) {
        return ticketRepository.findByAssigneeId(assigneeId);
    }

    public Ticket createTicket(CreateTicketRequest request) {
        Project project = projectService.getProjectById(request.getProjectId());
        User reporter = userService.getUserById(request.getReporterId());

        // Generate ticket number
        String ticketNumber = generateTicketNumber(project);

        Ticket ticket = new Ticket();
        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setTicketNumber(ticketNumber);
        ticket.setStatus(request.getStatus() != null ? request.getStatus() : TicketStatus.TODO);
        ticket.setPriority(request.getPriority());
        ticket.setProject(project);
        ticket.setReporter(reporter);
        ticket.setDueDate(request.getDueDate());

        if (request.getAssigneeId() != null) {
            User assignee = userService.getUserById(request.getAssigneeId());
            ticket.setAssignee(assignee);
        }

        if (request.getTagIds() != null && !request.getTagIds().isEmpty()) {
            Set<Tag> tags = new HashSet<>();
            for (Long tagId : request.getTagIds()) {
                Tag tag = tagRepository.findById(tagId)
                    .orElseThrow(() -> new RuntimeException("Tag not found with id: " + tagId));
                tags.add(tag);
            }
            ticket.setTags(tags);
        }

        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(Long id, CreateTicketRequest request) {
        Ticket ticket = getTicketById(id);

        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setStatus(request.getStatus());
        ticket.setPriority(request.getPriority());
        ticket.setDueDate(request.getDueDate());

        if (request.getAssigneeId() != null) {
            User assignee = userService.getUserById(request.getAssigneeId());
            ticket.setAssignee(assignee);
        } else {
            ticket.setAssignee(null);
        }

        if (request.getTagIds() != null) {
            Set<Tag> tags = new HashSet<>();
            for (Long tagId : request.getTagIds()) {
                Tag tag = tagRepository.findById(tagId)
                    .orElseThrow(() -> new RuntimeException("Tag not found with id: " + tagId));
                tags.add(tag);
            }
            ticket.setTags(tags);
        }

        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long id) {
        Ticket ticket = getTicketById(id);
        ticketRepository.delete(ticket);
    }

    private String generateTicketNumber(Project project) {
        List<Ticket> projectTickets = ticketRepository.findByProjectId(project.getId());
        int nextNumber = projectTickets.size() + 1;
        String ticketNumber;

        do {
            ticketNumber = project.getProjectKey() + "-" + nextNumber;
            nextNumber++;
        } while (ticketRepository.existsByTicketNumber(ticketNumber));

        return ticketNumber;
    }
}

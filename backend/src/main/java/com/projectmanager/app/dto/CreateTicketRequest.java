package com.projectmanager.app.dto;

import com.projectmanager.app.model.Ticket.TicketPriority;
import com.projectmanager.app.model.Ticket.TicketStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class CreateTicketRequest {
    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Project ID is required")
    private Long projectId;

    private TicketStatus status = TicketStatus.TODO;

    private TicketPriority priority = TicketPriority.MEDIUM;

    private Long assigneeId;

    @NotNull(message = "Reporter ID is required")
    private Long reporterId;

    private Set<Long> tagIds;

    private LocalDate dueDate;
}

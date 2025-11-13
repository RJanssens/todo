package com.projectmanager.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket_links")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "source_ticket_id", nullable = false)
    private Ticket sourceTicket;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "target_ticket_id", nullable = false)
    private Ticket targetTicket;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LinkType linkType;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum LinkType {
        BLOCKS("blocks"),
        BLOCKED_BY("is blocked by"),
        RELATES_TO("relates to"),
        DUPLICATES("duplicates"),
        DUPLICATED_BY("is duplicated by"),
        DEPENDS_ON("depends on"),
        DEPENDENCY_OF("is dependency of");

        private final String displayName;

        LinkType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
}

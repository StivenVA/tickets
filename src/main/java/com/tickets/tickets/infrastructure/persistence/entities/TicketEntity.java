package com.tickets.tickets.infrastructure.persistence.entities;


import com.tickets.tickets.domain.model.Ticket;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tickets")
@Builder
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private long id;

    @Column(name = "ticket_title")
    private String title;

    @Lob
    @Column(name = "ticket_description",columnDefinition = "TEXT")
    private String description;

    @Column(name = "ticket_comment")
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_status")
    private TicketStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name ="expired_at")
    private LocalDateTime expiredAt;

}

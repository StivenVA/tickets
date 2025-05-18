package com.tickets.tickets.infrastructure.persistence.entities;

import com.tickets.tickets.domain.model.UnresolvedTicket;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "unresolved_tickets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UnResolvedTicketEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_id")
    private TicketEntity ticketId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}

package com.tickets.infrastructure.persistence.entities;

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

    @Column(name = "stored_at")
    private LocalDateTime storedAt;

}

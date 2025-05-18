package com.tickets.tickets.infrastructure.controller;

import com.tickets.tickets.application.usecase.TicketUseCase;
import com.tickets.tickets.application.usecase.UnresolvedTicketUseCase;
import com.tickets.tickets.domain.model.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketUseCase ticketUseCase;
    private final UnresolvedTicketUseCase unresolvedTicketUseCase;

    @PostMapping
    public ResponseEntity<?> createTicket(@RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketUseCase.create(ticket.getTitle(), ticket.getDescription()));
    }

    @PutMapping("/resolve")
    public ResponseEntity<?> resolveTicket(@RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketUseCase.resolve(ticket.getId(),ticket.getComment()));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateTicket(@PathVariable Long id) {
        return ResponseEntity.ok(ticketUseCase.updateStatus(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllTickets() {
        return ResponseEntity.ok(ticketUseCase.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketUseCase.getById(id));
    }

    @GetMapping("/unresolved")
    public ResponseEntity<?> getUnresolvedTicketsPassed30Days() {
        return ResponseEntity.ok(unresolvedTicketUseCase.findPassed30Days());
    }
}

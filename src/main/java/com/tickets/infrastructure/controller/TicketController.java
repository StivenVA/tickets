package com.tickets.infrastructure.controller;

import com.tickets.application.dto.CreateTicketRequest;
import com.tickets.application.dto.ResolveTicketRequest;
import com.tickets.application.dto.ResponseTicket;
import com.tickets.application.dto.ResponseUnresolvedTicket;
import com.tickets.application.usecase.TicketUseCase;
import com.tickets.application.usecase.UnresolvedTicketUseCase;
import com.tickets.infrastructure.persistence.mapper.TicketMapper;
import com.tickets.infrastructure.persistence.mapper.UnresolvedTicketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketUseCase ticketUseCase;
    private final UnresolvedTicketUseCase unresolvedTicketUseCase;

    @PostMapping
    public ResponseEntity<ResponseTicket> createTicket(@RequestBody CreateTicketRequest ticket) {
        return ResponseEntity.ok(TicketMapper.toResponseDTO(ticketUseCase.create(ticket.getTitle(), ticket.getDescription())));
    }

    @PutMapping("/resolve")
    public ResponseEntity<ResponseTicket> resolveTicket(@RequestBody ResolveTicketRequest ticket) {
        return ResponseEntity.ok(TicketMapper.toResponseDTO(ticketUseCase.resolve(ticket.getId(),ticket.getComment())));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ResponseTicket> updateTicket(@PathVariable Long id) {
        return ResponseEntity.ok(TicketMapper.toResponseDTO(ticketUseCase.updateStatus(id)));
    }

    @GetMapping
    public ResponseEntity<List<ResponseTicket>> getAllTickets() {
        return ResponseEntity.ok(ticketUseCase.getAll().stream().map(TicketMapper::toResponseDTO).toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseTicket> getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(TicketMapper.toResponseDTO(ticketUseCase.getById(id)));
    }

    @GetMapping("/unresolved")
    public ResponseEntity<List<ResponseUnresolvedTicket>> getUnresolvedTicketsPassed30Days() {
        return ResponseEntity.ok(unresolvedTicketUseCase.findPassed30Days().stream().map(UnresolvedTicketMapper::toResponseDTO).toList());
    }
}

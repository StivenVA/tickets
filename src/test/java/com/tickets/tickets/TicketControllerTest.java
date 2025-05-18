package com.tickets.tickets;

import com.tickets.tickets.application.dto.CreateTicketRequest;
import com.tickets.tickets.application.dto.ResolveTicketRequest;
import com.tickets.tickets.application.usecase.TicketUseCase;
import com.tickets.tickets.application.usecase.UnresolvedTicketUseCase;
import com.tickets.tickets.domain.model.Ticket;
import com.tickets.tickets.domain.model.UnresolvedTicket;
import com.tickets.tickets.infrastructure.controller.TicketController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketControllerTest {

    private TicketUseCase ticketUseCase;
    private UnresolvedTicketUseCase unresolvedTicketUseCase;
    private TicketController controller;

    @BeforeEach
    void setup() {
        ticketUseCase = mock(TicketUseCase.class);
        unresolvedTicketUseCase = mock(UnresolvedTicketUseCase.class);
        controller = new TicketController(ticketUseCase, unresolvedTicketUseCase);
    }

    @Test
    void createTicket_shouldReturnCreatedTicketWithDates() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Bogota"));
        LocalDateTime expiration = now.plusDays(30);

        CreateTicketRequest ticket = CreateTicketRequest.builder()
                .title("Test")
                .description("Desc")
                .build();

        Ticket created = Ticket.builder()
                .id(1L)
                .title("Test")
                .description("Desc")
                .createdAt(now)
                .expiredAt(expiration)
                .build();

        when(ticketUseCase.create("Test", "Desc")).thenReturn(created);

        ResponseEntity<?> response = controller.createTicket(ticket);
        Ticket responseTicket = (Ticket) response.getBody();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(responseTicket);
        assertEquals("Test", responseTicket.getTitle());
        assertEquals("Desc", responseTicket.getDescription());

        assertEquals(now, responseTicket.getCreatedAt());
        assertEquals(expiration, responseTicket.getExpiredAt());
    }


    @Test
    void resolveTicket_shouldUpdateStatusToResolved() {

        LocalDateTime createdAt1 = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime createdAt2 = LocalDateTime.of(2025, 1, 1, 10, 0);

        ResolveTicketRequest originalTicket = ResolveTicketRequest.builder()
                .id(1L)
                .comment("Ticket completed")
                .build();

        Ticket resolvedTicket = Ticket.builder()
                .id(1L)
                .title("Sample")
                .status("RESOLVED")
                .createdAt(createdAt1)
                .updatedAt(LocalDateTime.now(ZoneId.of("America/Bogota")))
                .build();

        Ticket responseTicket = Ticket.builder()
                .id(1L)
                .title("Sample")
                .status("RESOLVED")
                .createdAt(createdAt2)
                .updatedAt(LocalDateTime.now(ZoneId.of("America/Bogota")))
                .build();

        when(ticketUseCase.resolve(originalTicket.getId(), originalTicket.getComment())).thenReturn(resolvedTicket);

        ResponseEntity<?> response = controller.resolveTicket(originalTicket);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("RESOLVED", ((Ticket) response.getBody()).getStatus());
        assertNotSame(responseTicket, response.getBody());
        assertEquals(responseTicket, response.getBody());
        assertEquals(responseTicket.getUpdatedAt(), ((Ticket) response.getBody()).getUpdatedAt());
        assertEquals(responseTicket.getCreatedAt(), ((Ticket) response.getBody()).getCreatedAt());
        assertEquals(responseTicket, response.getBody());
    }

    @Test
    void getAllTickets_shouldReturnList() {
        List<Ticket> tickets = List.of(new Ticket(), new Ticket());

        when(ticketUseCase.getAll()).thenReturn(tickets);

        ResponseEntity<?> response = controller.getAllTickets();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(tickets, response.getBody());
    }

    @Test
    void getTicketById_shouldReturnTicket() {
        Ticket ticket = Ticket.builder()
                .id(1L)
                .title("Example")
                .build();

        when(ticketUseCase.getById(1L)).thenReturn(ticket);

        ResponseEntity<?> response = controller.getTicketById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ticket, response.getBody());
    }

    @Test
    void getUnresolvedTickets_shouldReturnList() {
        List<?> unresolved = List.of(new Object());

        when(unresolvedTicketUseCase.findPassed30Days()).thenReturn((List<UnresolvedTicket>) unresolved);

        ResponseEntity<?> response = controller.getUnresolvedTicketsPassed30Days();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(unresolved, response.getBody());
    }


}

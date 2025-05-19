package com.tickets;

import com.tickets.application.dto.CreateTicketRequest;
import com.tickets.application.dto.ResolveTicketRequest;
import com.tickets.application.dto.ResponseTicket;
import com.tickets.application.dto.ResponseUnresolvedTicket;
import com.tickets.application.usecase.TicketUseCase;
import com.tickets.application.usecase.UnresolvedTicketUseCase;
import com.tickets.domain.model.Ticket;
import com.tickets.domain.model.UnresolvedTicket;
import com.tickets.infrastructure.controller.TicketController;
import com.tickets.infrastructure.controller.exception.ExceptionHandlerController;
import com.tickets.infrastructure.controller.exception.ExceptionResponse;
import com.tickets.infrastructure.controller.exception.TicketNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
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

    private ExceptionHandlerController controllerExceptionHandler;

    @BeforeEach
    void setup() {
        ticketUseCase = mock(TicketUseCase.class);
        unresolvedTicketUseCase = mock(UnresolvedTicketUseCase.class);
        controller = new TicketController(ticketUseCase, unresolvedTicketUseCase);
        controllerExceptionHandler = new ExceptionHandlerController();
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

        ResponseEntity<ResponseTicket> response = controller.createTicket(ticket);
        ResponseTicket responseTicket =response.getBody();

        assertEquals(200, response.getStatusCode().value());
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

        ResponseTicket responseTicket = ResponseTicket.builder()
                .id(1L)
                .title("Sample")
                .status("RESOLVED")
                .createdAt(createdAt2)
                .updatedAt(LocalDateTime.now(ZoneId.of("America/Bogota")))
                .build();

        when(ticketUseCase.resolve(originalTicket.getId(), originalTicket.getComment())).thenReturn(resolvedTicket);

        ResponseEntity<ResponseTicket> response = controller.resolveTicket(originalTicket);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("RESOLVED", response.getBody().getStatus());
        assertNotSame(responseTicket, response.getBody());
        assertEquals(responseTicket, response.getBody());
        assertEquals(responseTicket.getUpdatedAt(), ( response.getBody()).getUpdatedAt());
        assertEquals(responseTicket.getCreatedAt(), (response.getBody()).getCreatedAt());
        assertEquals(responseTicket, response.getBody());
    }

    @Test
    void getAllTickets_shouldReturnList() {
        List<Ticket> tickets = List.of(new Ticket(), new Ticket());

        List<ResponseTicket> responseTickets = List.of(new ResponseTicket(), new ResponseTicket());

        when(ticketUseCase.getAll()).thenReturn(tickets);

        ResponseEntity<List<ResponseTicket>> response = controller.getAllTickets();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(responseTickets, response.getBody());
    }

    @Test
    void getTicketById_shouldReturnTicket() {

        LocalDateTime createdAt = LocalDateTime.now(ZoneId.of("America/Bogota"));

        Ticket ticket = Ticket.builder()
                .id(1L)
                .title("Example")
                .description("Description")
                .status("OPEN")
                .createdAt(createdAt)
                .build();

        ResponseTicket responseTicket = ResponseTicket.builder()
                .id(1L)
                .title("Example")
                .description("Description")
                .status("OPEN")
                .createdAt(createdAt)
                .build();

        when(ticketUseCase.getById(1L)).thenReturn(ticket);

        ResponseEntity<ResponseTicket> response = controller.getTicketById(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(responseTicket, response.getBody());
    }

    @Test
    void getUnresolvedTickets_shouldReturnList() {
        Ticket ticket = new Ticket();

        UnresolvedTicket unresolvedTicket = new UnresolvedTicket();
        unresolvedTicket.setTicket(ticket);

        List<UnresolvedTicket> unresolved = List.of(unresolvedTicket);

        ResponseTicket responseTicket = new ResponseTicket();

        ResponseUnresolvedTicket responseUnresolvedTicket = new ResponseUnresolvedTicket();
        responseUnresolvedTicket.setTicket(responseTicket);

        List<ResponseUnresolvedTicket> unresolvedResponse = List.of(responseUnresolvedTicket);

        when(unresolvedTicketUseCase.findPassed30Days()).thenReturn(unresolved);

        ResponseEntity<List<ResponseUnresolvedTicket>> response = controller.getUnresolvedTicketsPassed30Days();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(unresolvedResponse, response.getBody());
    }

    @Test
    void get404CodeWhenTicketNotExist() {
        when(ticketUseCase.getById(1L)).thenThrow(new TicketNotFoundException("No ticket with id 1"));

        ResponseEntity<ExceptionResponse> response = controllerExceptionHandler.handleNotFound(
                new TicketNotFoundException("No ticket with id 1"),
                mock(HttpServletRequest.class)
        );

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("No ticket with id 1", response.getBody().message());
    }


}

package com.tickets.tickets.infrastructure.controller;

import com.tickets.tickets.application.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
}

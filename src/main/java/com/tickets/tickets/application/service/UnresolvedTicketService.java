package com.tickets.tickets.application.service;

import com.tickets.tickets.application.usecase.UnresolvedTicketUseCase;
import com.tickets.tickets.domain.model.UnresolvedTicket;
import com.tickets.tickets.domain.repository.UnresolvedTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnresolvedTicketService implements UnresolvedTicketUseCase {

    private final UnresolvedTicketRepository unresolvedTicketRepository;

    @Override
    public UnresolvedTicket save(UnresolvedTicket unresolvedTicket) {
        return unresolvedTicketRepository.save(unresolvedTicket);
    }

    @Override
    public List<UnresolvedTicket> findPassed30Days() {
        return unresolvedTicketRepository.findPassed30Days();
    }
}

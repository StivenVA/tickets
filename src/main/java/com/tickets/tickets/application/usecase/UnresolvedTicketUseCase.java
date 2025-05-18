package com.tickets.tickets.application.usecase;

import com.tickets.tickets.domain.model.UnresolvedTicket;

import java.util.List;

public interface UnresolvedTicketUseCase {

    UnresolvedTicket save(UnresolvedTicket unresolvedTicket);
    List<UnresolvedTicket> findPassed30Days();

    void runStoreUnresolvedTicketsProcedure();
}

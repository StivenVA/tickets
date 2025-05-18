package com.tickets.tickets.infrastructure.scheduler;

import com.tickets.tickets.application.usecase.UnresolvedTicketUseCase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private final UnresolvedTicketUseCase unresolvedTicketUseCase;

    @Scheduled(cron = "0/30 * * * * ?")
    public void runDailyProcedure() {
        unresolvedTicketUseCase.runStoreUnresolvedTicketsProcedure();
    }
}

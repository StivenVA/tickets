package com.tickets.infrastructure.scheduler;

import com.tickets.application.usecase.UnresolvedTicketUseCase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private final UnresolvedTicketUseCase unresolvedTicketUseCase;

    //@Scheduled(cron = "0/30 * * * * ?")
    @Scheduled(cron = "0 0 0 * * ?")
    public void runDailyProcedure() {
        unresolvedTicketUseCase.runStoreUnresolvedTicketsProcedure();
    }
}

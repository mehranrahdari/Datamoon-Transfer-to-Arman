package com.pedasco.datamoontransfertoarman.service;

import com.pedasco.datamoontransfertoarman.dto.LogEntryDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LogScheduler {

    private final LogFetcherService logService;

    public LogScheduler(LogFetcherService logService) {
        this.logService = logService;
    }

    @Scheduled(fixedRate = 3000)//30000
    public void scheduledFetch() {
        List<LogEntryDto> logs = logService.fetchLogsWithMinId(55L);
        logs.forEach(System.out::println);
    }
}
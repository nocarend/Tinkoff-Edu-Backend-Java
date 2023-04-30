package ru.tinkoff.edu.java.scrapper.service.updater;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.service.producer.Producer;

@EnableScheduling
@Log4j2
@Service
@RequiredArgsConstructor
public class LinkUpdaterSchedulerService {

    private final LinkUpdater linkUpdater;

    private final Producer producer;

    @Scheduled(fixedDelayString = "${app.scheduler.interval}")
    public void update() {
        var updates = linkUpdater.update();
        updates.forEach(producer::send);
    }

}

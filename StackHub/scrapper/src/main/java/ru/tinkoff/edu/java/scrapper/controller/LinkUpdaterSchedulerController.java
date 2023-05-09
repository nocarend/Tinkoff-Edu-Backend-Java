package ru.tinkoff.edu.java.scrapper.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import ru.tinkoff.edu.java.scrapper.service.updater.LinkUpdater;
@EnableScheduling
@Controller
@Log4j2
@RequiredArgsConstructor
public class LinkUpdaterSchedulerController {

    private final LinkUpdater linkUpdater;

    @Scheduled(fixedDelayString = "${app.scheduler.interval}")
    public void update() {
        linkUpdater.update();
    }

}

package ru.tinkoff.edu.java.scrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class LinkUpdaterScheduler {

    private static final Logger LOGGER = LogManager.getLogger(LinkUpdaterScheduler.class.getName());

    @Scheduled(fixedDelayString = "#{@delay}")
    public void update() {
        LOGGER.info("Some info.");
    }

}

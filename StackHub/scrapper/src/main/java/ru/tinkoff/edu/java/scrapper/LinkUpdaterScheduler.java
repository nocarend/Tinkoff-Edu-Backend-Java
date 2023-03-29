package ru.tinkoff.edu.java.scrapper;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Log4j2
public class LinkUpdaterScheduler {


    @Scheduled(fixedDelayString = "#{@delay}")
    public void update() {
        log.info("Some info.");
    }

}

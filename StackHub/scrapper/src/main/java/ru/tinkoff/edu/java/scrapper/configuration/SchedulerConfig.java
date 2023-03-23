package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    @Bean("delay")
    public long getDelay(ApplicationConfig config) {
        return config.scheduler().interval().toMillis();
    }

}

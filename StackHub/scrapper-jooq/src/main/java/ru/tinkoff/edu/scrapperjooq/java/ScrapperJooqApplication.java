package ru.tinkoff.edu.scrapperjooq.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableConfigurationProperties(ApplicationConfig.class)
@EnableScheduling
public class ScrapperJooqApplication {

    public static void main(String[] args) {
        var ctx = SpringApplication.run(ScrapperJooqApplication.class, args);
    }
}
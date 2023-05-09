package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * Configuration of bot app.
 *
 * @param queue    message broker queue name
 * @param bind     message broker binding name
 * @param exchange message broker exchange name
 * @param useQueue false - using only http requests, true - using http and message broker
 */
@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(String queue, String bind, String exchange, boolean useQueue) {

}
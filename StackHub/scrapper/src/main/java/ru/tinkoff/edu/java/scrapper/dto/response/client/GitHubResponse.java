package ru.tinkoff.edu.java.scrapper.dto.response.client;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

public record GitHubResponse(long id, boolean active, Timestamp updatedAt,
                             OffsetDateTime createdAt) implements LinkResponseMark {

}

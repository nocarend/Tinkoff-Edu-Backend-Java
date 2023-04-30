package ru.tinkoff.edu.java.scrapper.dto.response.client;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

public record GitHubResponse(long id, boolean active, OffsetDateTime updatedAt,
                             OffsetDateTime createdAt) implements LinkResponseMark {

}

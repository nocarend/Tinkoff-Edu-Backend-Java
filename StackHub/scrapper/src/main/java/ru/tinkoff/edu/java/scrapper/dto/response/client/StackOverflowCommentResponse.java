package ru.tinkoff.edu.java.scrapper.dto.response.client;

import java.time.OffsetDateTime;

public record StackOverflowCommentResponse(OffsetDateTime creation, long score) {

}

package ru.tinkoff.edu.java.scrapper.dto.response.client;

import java.time.OffsetDateTime;

public record StackOverflowAnswerResponse(OffsetDateTime activity, OffsetDateTime creation,
                                          long votes) {

}

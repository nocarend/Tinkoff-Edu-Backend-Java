package ru.tinkoff.edu.java.scrapper.dto.response.client;

import java.time.OffsetDateTime;

public record StackOverflowResponse(OffsetDateTime activity, OffsetDateTime creation,
                                    long votes) implements
    LinkResponse {

}

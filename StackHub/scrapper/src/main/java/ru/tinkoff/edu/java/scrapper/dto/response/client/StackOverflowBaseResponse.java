package ru.tinkoff.edu.java.scrapper.dto.response.client;

import java.sql.Timestamp;
import java.time.OffsetDateTime;

public record StackOverflowBaseResponse(OffsetDateTime activity, OffsetDateTime creation,
                                        long votes) implements
    LinkResponseMark {

}

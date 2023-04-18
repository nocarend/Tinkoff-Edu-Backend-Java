package ru.tinkoff.edu.java.scrapper.dto.response.client;

import java.sql.Timestamp;

public record StackOverflowResponse(Timestamp activity, Timestamp creation,
                                    long votes) implements
    LinkResponseMark {

}

package ru.tinkoff.edu.java.scrapper.repository.dto;

import java.net.URI;
import java.sql.Timestamp;

public record LinkRepositoryResponse(long id, URI url, Timestamp updatedAt) {

}

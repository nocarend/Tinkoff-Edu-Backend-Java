package ru.tinkoff.edu.java.scrapper.repository.dto;

import java.net.URI;
import java.sql.Timestamp;

public record AddLinkRequest(URI url, Timestamp updatedAt) {

}

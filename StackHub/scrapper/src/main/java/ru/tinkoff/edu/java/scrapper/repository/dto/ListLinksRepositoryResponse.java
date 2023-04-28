package ru.tinkoff.edu.java.scrapper.repository.dto;

import java.util.List;
import ru.tinkoff.edu.java.scrapper.dto.response.ListLinksResponse;

public record ListLinksRepositoryResponse(List<LinkRepositoryResponse> links) {

}

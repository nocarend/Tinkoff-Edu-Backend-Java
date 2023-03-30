package ru.tinkoff.edu.java.scrapper.dto.response;

import java.util.List;

/**
 * ListLinksResponse
 */

public record ListLinksResponse(List<LinkResponse> links, int size) {

}
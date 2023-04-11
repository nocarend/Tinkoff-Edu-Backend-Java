package ru.tinkoff.edu.java.scrapper.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.scrapper.dto.request.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.response.ApiErrorResponse;
import ru.tinkoff.edu.java.scrapper.dto.response.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.response.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.service.UserService;

@RequestMapping("/links")
@RestController
@RequiredArgsConstructor
public class LinksController {

    private final UserService userService;

    @Operation(summary = "Получить все отслеживаемые ссылки")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ссылки успешно получены", content = {
            @Content(mediaType = "application/json", schema =
            @Schema(implementation = ListLinksResponse.class))
        })})
    @GetMapping
    public ListLinksResponse getAllLinks(@RequestHeader long tgChatId) {
        return userService.getLinksFromChatId(tgChatId);
    }

    @Operation(summary = "Добавить отслеживание ссылки")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ссылка успешно добавлена", content = {
            @Content(mediaType = "appilcation/json", schema =
            @Schema(implementation = LinkResponse.class))
        })
    })
    @PostMapping
    public LinkResponse addLink(@RequestHeader long tgChatId,
        @RequestBody AddLinkRequest link) {
        return userService.addLink(tgChatId, link.link());
    }

    @Operation(summary = "Убрать отслеживание ссылки")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ссылка успешно убрана", content = {
            @Content(mediaType = "application/json", schema =
            @Schema(implementation = LinkResponse.class))
        }),
        @ApiResponse(responseCode = "404", description = "Ссылка не найдена", content = {
            @Content(mediaType = "application/json", schema =
            @Schema(implementation = ApiErrorResponse.class))
        })
    })
    @DeleteMapping
    public LinkResponse removeLink(@RequestHeader long tgChatId,
        @RequestBody RemoveLinkRequest link) {
        return userService.deleteLink(tgChatId, link.link());
    }
}
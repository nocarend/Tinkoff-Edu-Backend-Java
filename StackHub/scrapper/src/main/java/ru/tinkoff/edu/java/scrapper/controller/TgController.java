package ru.tinkoff.edu.java.scrapper.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.scrapper.dto.response.ApiErrorResponse;
import ru.tinkoff.edu.java.scrapper.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tg-chat")
public class TgController {

    private final UserService userService;

    @Operation(summary = "Зарегистрировать чат")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Чат зарегистрирован"),
    })
    @PostMapping("/{chatId}")
    public void registerChat(@PathVariable long chatId) {
        userService.create(chatId);
    }

    @Operation(summary = "Удалить чат")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Чат успешно удалён"),
        @ApiResponse(responseCode = "404", description = "Чат не существует", content = {
            @Content(mediaType = "application/json",
                schema = @Schema(implementation = ApiErrorResponse.class))})
    })
    @DeleteMapping("/tg-chat/{chatId}")
    public void deleteChat(@PathVariable long chatId) {
        userService.delete(chatId);
    }
}

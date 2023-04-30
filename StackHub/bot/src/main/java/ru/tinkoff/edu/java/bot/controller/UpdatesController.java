package ru.tinkoff.edu.java.bot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.bot.dto.request.LinkUpdateRequest;
import ru.tinkoff.edu.java.bot.service.Updater;
import ru.tinkoff.edu.java.bot.telegram.StackHubBot;

@RestController
@RequiredArgsConstructor
public class UpdatesController {

    private final StackHubBot bot;
    private final Updater updater;

    @Operation(summary = "Отправить обновление")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Обновление обработано"),
    })
    @PostMapping("/updates")
    public ResponseEntity<Void> updatesPost(@RequestBody LinkUpdateRequest linkUpdate) {
        updater.update(linkUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

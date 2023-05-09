package ru.tinkoff.edu.java.bot;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.tinkoff.edu.java.bot.dto.response.ApiErrorResponse;

/**
 * a.
 */
@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса", content = {
        @Content(mediaType = "application/json",
            schema = @Schema(implementation = ApiErrorResponse.class))})
    @ExceptionHandler(UnsupportedOperationException.class)
    protected ResponseEntity<ApiErrorResponse> methodArgumentNotValid(
        MethodArgumentNotValidException exception) {
        final String description = "Something";
        return new ResponseEntity<>(
            new ApiErrorResponse(description, exception.getStatusCode().toString(),
                exception.getClass().getName(), exception.getMessage(),
                Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toList()),
            HttpStatus.BAD_REQUEST);
    }
}
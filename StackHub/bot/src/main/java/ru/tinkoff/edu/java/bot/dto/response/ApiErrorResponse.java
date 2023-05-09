package ru.tinkoff.edu.java.bot.dto.response;

import java.util.List;

/**
 * Response to error handler's exceptions.
 *
 * @param description      error description
 * @param code             error code value
 * @param exceptionName    exception name
 * @param exceptionMessage error message
 * @param stacktrace       all exception's stacktrace
 */
public record ApiErrorResponse(String description, String code, String exceptionName,
                               String exceptionMessage, List<String> stacktrace) {

}
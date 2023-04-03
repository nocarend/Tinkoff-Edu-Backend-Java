package ru.tinkoff.edu.java.linkparser.url;

import lombok.NonNull;

public record StackOverflow(@NonNull String questionId) implements ParsedValue {

}

package ru.tinkoff.edu.java.linkparser.url;

import lombok.NonNull;

public record Github(@NonNull String username, @NonNull String repositoryName) implements
    ParsedValue {

}

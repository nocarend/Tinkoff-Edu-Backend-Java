package ru.tinkoff.edu.java.linkparser;

public sealed interface ParsedValue permits Github, StackOverflow {
}

record Github(String username, String repositoryName) implements ParsedValue {
}

record StackOverflow(String questionId) implements ParsedValue {
}